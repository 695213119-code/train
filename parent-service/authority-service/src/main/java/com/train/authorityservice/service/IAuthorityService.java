package com.train.authorityservice.service;

import com.train.authorityservice.dto.AddRoleDTO;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.entityservice.entity.authority.Role;
import com.train.entityservice.entity.vo.UserAuthorityVO;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * 权限模块接口
 *
 * @author zhangLei
 * @serial 2019/09/21
 */
public interface IAuthorityService {


    /**
     * 查询用户权限
     *
     * @param userId 用户id
     * @return List
     */
    List<UserAuthorityVO> getUserAuthorityServiceInvocation(Long userId);

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
}
