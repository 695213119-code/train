package com.train.authorityservice.service;

import com.train.authorityservice.dto.AddRoleAuthorityDTO;
import com.train.authorityservice.dto.AddRoleDTO;
import com.train.authorityservice.dto.EditRoleDTO;
import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.entityservice.entity.authority.Role;
import org.springframework.validation.BindingResult;

/**
 * 角色APi接口类
 *
 * @author zhangLei
 * @serial 2019/09/24
 */
public interface ISystemRoleService {

    /**
     * 添加角色
     *
     * @param roleDTO       角色参数
     * @param bindingResult BindingResult
     * @return RespRecurrence
     */
    RespRecurrence addRole(AddRoleDTO roleDTO, BindingResult bindingResult);


    /**
     * 根据角色id查询角色
     *
     * @param roleId 角色id
     * @return RoleVO
     */
    Role getRoleServiceInvocation(Long roleId);

    /**
     * 获取角色列表
     *
     * @param page     page
     * @param limit    limit
     * @param roleName 角色名称
     * @return RespPageRecurrence
     */
    RespPageRecurrence queryRoleTabulation(Integer page, Integer limit, String roleName);

    /**
     * 修改角色
     *
     * @param editRoleDTO   角色参数
     * @param bindingResult BindingResult
     * @return RespRecurrence
     */
    RespRecurrence editRole(EditRoleDTO editRoleDTO, BindingResult bindingResult);

    /**
     * 角色赋权
     *
     * @param addRoleAuthorityDTO 赋权参数
     * @param bindingResult       BindingResult
     * @return RespRecurrence
     */
    RespRecurrence roleEmpowerment(AddRoleAuthorityDTO addRoleAuthorityDTO, BindingResult bindingResult);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return RespRecurrence
     */
    RespRecurrence deleteRole(Long roleId);
}
