package com.train.authorityservice.service;

import com.train.authorityservice.dto.AddRoleDTO;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.entityservice.entity.authority.Jurisdiction;
import com.train.entityservice.entity.authority.Role;
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
    List<Jurisdiction> getUserAuthorityServiceInvocation(Long userId);

}
