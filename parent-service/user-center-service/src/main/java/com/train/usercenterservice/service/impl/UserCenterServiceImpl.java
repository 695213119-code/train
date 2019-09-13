package com.train.usercenterservice.service.impl;

import com.train.commonservice.recurrence.RespRecurrence;
import com.train.usercenterservice.dto.UserRegisterDTO;
import com.train.usercenterservice.user.entity.User;
import com.train.usercenterservice.service.IUserCenterService;
import com.train.usercenterservice.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户中心实现类
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
@Service
public class UserCenterServiceImpl implements IUserCenterService {

    private final IUserService userService;

    @Autowired
    public UserCenterServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public RespRecurrence userRegister(UserRegisterDTO userRegisterDTO) {

        //TODO 校验手机验证码

        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);

        try {
            userService.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RespRecurrence<String>().success("用户注册成功");
    }
}
