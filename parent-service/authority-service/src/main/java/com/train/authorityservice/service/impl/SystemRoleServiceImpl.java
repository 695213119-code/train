package com.train.authorityservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.train.authorityservice.authority.service.IJurisdictionService;
import com.train.authorityservice.authority.service.IRoleJurisdictionService;
import com.train.authorityservice.authority.service.IRoleService;
import com.train.authorityservice.dto.AddRoleDTO;
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

        //获取所有权限值,并且递归分类
        List<AuthorityVO> authorityCategorizationList = categorizationAuthority();

        //获取所有的角色当前权限
        Long[] jurisdictionArray = new Long[roleTabulations.size()];
        for (int a = 0; a < jurisdictionArray.length; a++) {
            jurisdictionArray[a] = roleTabulations.get(a).getId();
        }
        List<RoleJurisdiction> roleJurisdictions = roleJurisdictionService.selectList(new EntityWrapper<RoleJurisdiction>()
                .in(SqlConstant.SQL_FIELD_ROLE_ID, jurisdictionArray));

        //保存权限至角色数据中
        roleTabulations.forEach(x -> {
            List<AuthorityVO> authorityList = new ArrayList<>();
            x.setRoleAuthority(authorityCategorizationList);
        });

        //将权限根据角色分类
        Map<String, List<Long>> map = new HashMap<>(32);
        roleJurisdictions.forEach(x -> {
            String roleId = String.valueOf(x.getRoleId());
            List<Long> list = map.get(roleId);
            if (CollUtil.isNotEmpty(list)) {
                list.add(x.getJurisdictionId());
                map.put(roleId, list);
            } else {
                List<Long> newList = new ArrayList<>();
                newList.add(x.getJurisdictionId());
                map.put(roleId, newList);
            }
        });

        //角色对应的权限赋值
        roleTabulations.forEach(x -> {
            List<Long> authorityIdList = map.get(String.valueOf(x.getId()));
            if (CollUtil.isNotEmpty(authorityIdList)) {
                x.setRoleAuthority(assignment(x.getRoleAuthority(), authorityIdList));
            }
        });

        return new RespPageRecurrence<>().success(roleTabulations, MybatisPageConvertRespPageUtils.convert(pages));
    }

//    private List<AuthorityVO> recursionClone(List<AuthorityVO> authorityCategorizationList) {
//        List<AuthorityVO> authorityListEnd = new ArrayList<>(authorityCategorizationList);
//        authorityListEnd.forEach(x -> {
//
//            x.setRoleAuthorityChildren();
//        });
//    }



    /**
     * 角色对应权限递归赋值
     *
     * @param roleAuthority   当前角色的[所有的权限]
     * @param authorityIdList 当前角色拥有的权限
     * @return List<AuthorityVO>
     */
    private List<AuthorityVO> assignment(List<AuthorityVO> roleAuthority, List<Long> authorityIdList) {
        if (CollUtil.isNotEmpty(roleAuthority)) {
            roleAuthority.forEach(x -> {
                if (authorityIdList.contains(x.getId())) {
                    x.setItExist(2);
                }
                if (CollUtil.isNotEmpty(x.getRoleAuthorityChildren())) {
                    assignment(x.getRoleAuthorityChildren(), authorityIdList);
                }
            });
        }
        return roleAuthority;
    }

    /**
     * 权限分类
     *
     * @return List<AuthorityVO>
     */
    private List<AuthorityVO> categorizationAuthority() {
        List<Jurisdiction> jurisdictionAll = jurisdictionService.selectList(new EntityWrapper<Jurisdiction>()
                .eq(CommonConstant.SQL_DELETE_SIGN, CommonConstant.SQL_DELETE_SIGN_NOT));
        List<AuthorityVO> authorityAll = new ArrayList<>();
        jurisdictionAll.forEach(x -> {
            AuthorityVO authority = new AuthorityVO();
            BeanUtils.copyProperties(x, authority);
            authorityAll.add(authority);
        });
        //定义分类权限最终返回集合
        List<AuthorityVO> authorityEnd = new ArrayList<>();
        //获取一级权限
        Long topAuthority = 0L;
        authorityAll.forEach(x -> {
            if (topAuthority.equals(x.getParentId())) {
                authorityEnd.add(x);
            }
        });
        for (AuthorityVO x : authorityEnd) {
            List<AuthorityVO> childrenAuthority = getChildrenAuthority(x.getId(), authorityAll);
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

}
