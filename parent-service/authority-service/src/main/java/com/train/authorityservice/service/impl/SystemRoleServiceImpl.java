package com.train.authorityservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.train.authorityservice.authority.service.IJurisdictionService;
import com.train.authorityservice.authority.service.IRoleJurisdictionService;
import com.train.authorityservice.authority.service.IRoleService;
import com.train.authorityservice.dto.AddRoleAuthorityDTO;
import com.train.authorityservice.dto.AddRoleDTO;
import com.train.authorityservice.dto.EditRoleDTO;
import com.train.authorityservice.mapper.SystemRoleMapper;
import com.train.authorityservice.service.ISystemRoleService;
import com.train.authorityservice.utils.MybatisPageConvertRespPageUtils;
import com.train.authorityservice.vo.AuthorityVO;
import com.train.authorityservice.vo.RoleTabulationVO;
import com.train.commonservice.constant.CommonConstant;
import com.train.commonservice.constant.SqlConstant;
import com.train.commonservice.enumeration.CommonEnum;
import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.entityservice.entity.authority.Jurisdiction;
import com.train.entityservice.entity.authority.Role;
import com.train.entityservice.entity.authority.RoleJurisdiction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

/**
 * 角色APi实现类
 *
 * @author zhangLei
 * @serial 2019/09/24
 */
@Service
public class SystemRoleServiceImpl implements ISystemRoleService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SystemRoleServiceImpl.class);

    private final IRoleService roleService;
    private final SystemRoleMapper systemRoleMapper;
    private final IJurisdictionService jurisdictionService;
    private final IRoleJurisdictionService roleJurisdictionService;

    @Autowired
    public SystemRoleServiceImpl(IRoleService roleService, SystemRoleMapper systemRoleMapper, IJurisdictionService jurisdictionService, IRoleJurisdictionService roleJurisdictionService) {
        this.roleService = roleService;
        this.systemRoleMapper = systemRoleMapper;
        this.jurisdictionService = jurisdictionService;
        this.roleJurisdictionService = roleJurisdictionService;
    }


    @Override
    public RespRecurrence addRole(AddRoleDTO roleDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RespRecurrence().failure(CommonEnum.INVALID_PARAMETER.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Role roleCheck = roleService.selectOne(new EntityWrapper<Role>().eq(SqlConstant.SQL_FIELD_ROLE_NAME, roleDTO.getRoleName()).
                eq(CommonConstant.SQL_DELETE_SIGN, CommonConstant.SQL_DELETE_SIGN_NOT));
        if (null != roleCheck && null != roleCheck.getId()) {
            return new RespRecurrence().failure(CommonEnum.BUSINESS_CODE.getCode(), "角色名称不允许重复");
        }

        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);

        try {
            roleService.insert(role);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加角色失败,,参数:{}", roleDTO);
        }
        return new RespRecurrence().success();
    }


    @Override
    public Role getRoleServiceInvocation(Long roleId) {
        return roleService.selectById(roleId);
    }

    @Override
    public RespPageRecurrence queryRoleTabulation(Integer page, Integer limit, String roleName) {

        Page<RoleTabulationVO> pages = new Page<>(page, limit);
        List<RoleTabulationVO> roleTabulations = systemRoleMapper.queryRoleTabulation(pages, roleName);

        if (CollUtil.isNotEmpty(roleTabulations)) {

            //获取所有的角色当前权限
            Long[] jurisdictionIdsArray = new Long[roleTabulations.size()];
            for (int a = 0; a < jurisdictionIdsArray.length; a++) {
                jurisdictionIdsArray[a] = roleTabulations.get(a).getId();
            }
            List<RoleJurisdiction> roleJurisdictions = roleJurisdictionService.selectList(new EntityWrapper<RoleJurisdiction>()
                    .in(SqlConstant.SQL_FIELD_ROLE_ID, jurisdictionIdsArray));

            //将权限根据角色分类
            Map<String, List<Long>> classifyMap = new HashMap<>(32);
            roleJurisdictions.forEach(x -> {
                String roleId = String.valueOf(x.getRoleId());
                List<Long> list = classifyMap.get(roleId);
                if (CollUtil.isNotEmpty(list)) {
                    list.add(x.getJurisdictionId());
                    classifyMap.put(roleId, list);
                } else {
                    List<Long> newList = new ArrayList<>();
                    newList.add(x.getJurisdictionId());
                    classifyMap.put(roleId, newList);
                }
            });

            //获取所有权限
            List<Jurisdiction> jurisdictionAll = jurisdictionService.selectList(new EntityWrapper<Jurisdiction>()
                    .eq(CommonConstant.SQL_DELETE_SIGN, CommonConstant.SQL_DELETE_SIGN_NOT));

            //将权限数据存入指定对象
            List<AuthorityVO> authorityAll = new ArrayList<>();
            jurisdictionAll.forEach(x -> {
                AuthorityVO authority = new AuthorityVO();
                BeanUtils.copyProperties(x, authority);
                authorityAll.add(authority);
            });

            //角色赋权
            roleTabulations.forEach(x -> {
                List<AuthorityVO> authorityClone = new ArrayList<>();
                if (CollUtil.isNotEmpty(authorityAll)) {
                    //深克隆
                    authorityAll.forEach(c -> {
                        try {
                            authorityClone.add(c.clone());
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    });
                }
                if (CollUtil.isNotEmpty(authorityClone)) {
                    List<Long> authorityIdArray = classifyMap.get(String.valueOf(x.getId()));
                    List<String> selectPermissions = new ArrayList<>();
                    if (CollUtil.isNotEmpty(authorityIdArray)) {
                        //确定角色具有的权限
                        authorityClone.forEach(a -> {
                            if (authorityIdArray.contains(a.getId())) {
                                selectPermissions.add(String.valueOf(a.getId()));
                            }
                        });
                        x.setRoleAuthority(authorityClone);
                        x.setSelectPermissions(selectPermissions);
                    } else {
                        x.setRoleAuthority(authorityClone);
                    }
                }
            });

            //角色的权限递归分类
            roleTabulations.forEach(x -> {
                List<AuthorityVO> roleAuthority = x.getRoleAuthority();
                if (CollUtil.isNotEmpty(roleAuthority)) {
                    x.setRoleAuthority(categorizationAuthority(roleAuthority));
                }
            });
        }

        roleTabulations.forEach(x -> {
            LOGGER.info("角色[" + x.getRoleName() + "]选中的权限id:" + x.getSelectPermissions());
        });


        return new RespPageRecurrence<>().success(roleTabulations, MybatisPageConvertRespPageUtils.convert(pages));
    }

    @Override
    public RespRecurrence editRole(EditRoleDTO editRoleDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RespRecurrence().failure(CommonEnum.INVALID_PARAMETER.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Role roleCheck = roleService.selectOne(new EntityWrapper<Role>().eq(SqlConstant.SQL_FIELD_ROLE_NAME, editRoleDTO.getRoleName()).
                eq(CommonConstant.SQL_DELETE_SIGN, CommonConstant.SQL_DELETE_SIGN_NOT));
        if ((null != roleCheck) && (null != roleCheck.getId()) && (!roleCheck.getId().equals(editRoleDTO.getId()))) {
            return new RespRecurrence().failure(CommonEnum.BUSINESS_CODE.getCode(), "角色名称不允许重复");
        }


        Role role = new Role();
        BeanUtils.copyProperties(editRoleDTO, role);
        role.setUpdateTime(new Date());

        try {
            roleService.updateById(role);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改角色失败,,参数:{}", editRoleDTO);
        }

        return new RespRecurrence().success();
    }

    @Override
    public RespRecurrence roleEmpowerment(AddRoleAuthorityDTO addRoleAuthorityDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RespRecurrence().failure(CommonEnum.INVALID_PARAMETER.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Role role = roleService.selectById(addRoleAuthorityDTO.getRoleId());

        if (null == role) {
            return new RespRecurrence().failure(CommonEnum.BUSINESS_CODE.getCode(), "非法的角色");
        }

        //删除角色原有的权限
        List<RoleJurisdiction> roleJurisdictions = roleJurisdictionService.selectList(new EntityWrapper<RoleJurisdiction>().eq(SqlConstant.SQL_FIELD_ROLE_ID, role.getId()));
        if (CollUtil.isNotEmpty(roleJurisdictions)) {
            List<Long> roleJurisdictionIdArray = new ArrayList<>();
            roleJurisdictions.forEach(x -> {
                roleJurisdictionIdArray.add(x.getId());
            });
            try {
                roleJurisdictionService.deleteBatchIds(roleJurisdictionIdArray);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("删除角色原有权限失败,参数:{}", addRoleAuthorityDTO);
            }
        }

        Long[] roleAuthority = addRoleAuthorityDTO.getRoleAuthority();
        //角色重新赋权 只将操作权限赋值到角色下
        if (null != roleAuthority && roleAuthority.length > 0) {
            List<Long> authorityEnd = new ArrayList<>();
            List<Jurisdiction> jurisdictionList = jurisdictionService.selectList(new EntityWrapper<>());
            for (Long id : roleAuthority) {
                if (!judgingWhetherThereIsASubset(id, jurisdictionList)) {
                    authorityEnd.add(id);
                }
            }
            List<RoleJurisdiction> roleJurisdictionsNew = new ArrayList<>();
            for (Long id : authorityEnd) {
                RoleJurisdiction roleJurisdiction = new RoleJurisdiction();
                roleJurisdiction.setRoleId(role.getId());
                roleJurisdiction.setJurisdictionId(id);
                roleJurisdictionsNew.add(roleJurisdiction);
            }
            try {
                roleJurisdictionService.insertBatch(roleJurisdictionsNew);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("角色赋权失败,参数:{} {}", addRoleAuthorityDTO, roleAuthority);
            }
        }
        return new RespRecurrence().success();
    }

    @Override
    public RespRecurrence deleteRole(Long roleId) {

        Role role = roleService.selectById(roleId);
        if (null == role) {
            return new RespRecurrence().failure(CommonEnum.BUSINESS_CODE.getCode(), "非法的角色");
        }
        //该角色下存在权限,不可被删除
        List<RoleJurisdiction> roleJurisdictions = roleJurisdictionService.selectList(new EntityWrapper<RoleJurisdiction>().eq(SqlConstant.SQL_FIELD_ROLE_ID, role.getId()));
        if (CollUtil.isNotEmpty(roleJurisdictions)) {
            return new RespRecurrence().failure(CommonEnum.BUSINESS_CODE.getCode(), "该角色[" + role.getRoleName() + "]下存在权限,不允许删除");
        }

        try {
            roleService.deleteById(role.getId());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除角色失败,参数:{}", roleId);
        }

        return new RespRecurrence().success();
    }


    /**
     * 角色下的权限递归分类
     *
     * @param roleAuthority 角色下的权限数据集合[本质就是所有的权限]
     * @return List<AuthorityVO>
     */
    private List<AuthorityVO> categorizationAuthority(List<AuthorityVO> roleAuthority) {
        //定义分类权限最终返回集合
        List<AuthorityVO> authorityEnd = new ArrayList<>();
        //获取一级权限
        Long topAuthority = 0L;
        roleAuthority.forEach(x -> {
            if (topAuthority.equals(x.getParentId())) {
                authorityEnd.add(x);
            }
        });
        for (AuthorityVO x : authorityEnd) {
            List<AuthorityVO> childrenAuthority = getChildrenAuthority(x.getId(), roleAuthority);
            if (CollUtil.isNotEmpty(childrenAuthority)) {
                x.setRoleAuthorityChildren(childrenAuthority);
            } else {
                x.setRoleAuthorityChildren(Collections.emptyList());
            }
        }
        return authorityEnd;
    }


    /**
     * 递归获取子权限
     *
     * @param authorityId  当前权限id
     * @param authorityAll 所有权限数据集合
     * @return List<AuthorityVO>
     */
    private List<AuthorityVO> getChildrenAuthority(Long authorityId, List<AuthorityVO> authorityAll) {
        List<AuthorityVO> childrenAuthority = new ArrayList<>();
        authorityAll.forEach(x -> {
            if (x.getParentId() != 0 && x.getParentId().equals(authorityId)) {
                childrenAuthority.add(x);
            }
        });
        childrenAuthority.forEach(x -> {
            x.setRoleAuthorityChildren(getChildrenAuthority(x.getId(), authorityAll));
        });
        if (childrenAuthority.size() == 0) {
            return null;
        }
        return childrenAuthority;
    }


    /**
     * 判断权限是否存在子集
     *
     * @param id               权限id
     * @param jurisdictionList 权限集合
     * @return boolean true 存在 false 不存在
     */
    private boolean judgingWhetherThereIsASubset(Long id, List<Jurisdiction> jurisdictionList) {
        int sign = 1;
        if (CollUtil.isNotEmpty(jurisdictionList)) {
            for (Jurisdiction jurisdiction : jurisdictionList) {
                if (jurisdiction.getParentId().equals(id)) {
                    sign = 2;
                    break;
                }
            }
        }
        return sign != 1;
    }

}
