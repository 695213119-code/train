package com.train.usercenterservice.user.service.impl;

//需要修改导包位置

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.train.entityservice.entity.user.User;
import com.train.usercenterservice.user.mapper.UserMapper;
import com.train.usercenterservice.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2019-09-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    private final UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


}
