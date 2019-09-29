package com.train.authorityservice.service;

import com.train.authorityservice.dto.AddAuthorityDTO;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.entityservice.entity.authority.Jurisdiction;
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

    /**
     * 添加权限
     *
     * @param authorityAddDTO 权限DTO
     * @param bindingResult   BindingResult
     * @return RespRecurrence
     */
    RespRecurrence addAuthority(AddAuthorityDTO authorityAddDTO, BindingResult bindingResult);


}
