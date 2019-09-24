package com.train.authorityservice.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.train.authorityservice.authority.service.IRoleService;
import com.train.authorityservice.dto.AddRoleDTO;
import com.train.authorityservice.mapper.AuthorityMapper;
import com.train.authorityservice.service.IAuthorityService;
import com.train.commonservice.constant.CommonConstant;
import com.train.commonservice.constant.SqlConstant;
import com.train.commonservice.enumeration.CommonEnum;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.entityservice.entity.authority.Jurisdiction;
import com.train.entityservice.entity.authority.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * 权限模块实现类
 *
 * @author zhangLei
 * @serial 2019/09/21
 */

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceImpl.class);


    private final AuthorityMapper authorityMapper;
    private final IRoleService roleService;

    @Autowired
    public AuthorityServiceImpl(AuthorityMapper authorityMapper, IRoleService roleService) {
        this.authorityMapper = authorityMapper;
        this.roleService = roleService;
    }

    @Override
    public List<Jurisdiction> getUserAuthorityServiceInvocation(Long userId) {
        return authorityMapper.getUserAuthority(userId);
    }



}
