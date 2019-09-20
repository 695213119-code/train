package com.train.authorityservice.service.impl;

import com.train.authorityservice.mapper.AuthorityMapper;
import com.train.authorityservice.service.IAuthorityService;
import com.train.entityservice.entity.vo.UserAuthorityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限模块实现类
 *
 * @author zhangLei
 * @serial 2019/09/21
 */

@Service
public class AuthorityServiceImpl implements IAuthorityService {


    private final AuthorityMapper authorityMapper;

    @Autowired
    public AuthorityServiceImpl(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    @Override
    public List<UserAuthorityVO> getUserAuthority(Long userId) {
        return authorityMapper.getUserAuthority(userId);
    }
}
