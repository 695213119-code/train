package com.train.usercenterservice.user.service;

import com.train.commonservice.recurrence.RespRecurrence;
import com.train.usercenterservice.dto.UserRegisterDTO;

/**
 * 用户中心接口类
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
public interface IUserCenterService {

    /**
     * 用户注册
     *
     * @param userRegisterDTO 用户注册参数类
     * @return RespRecurrence
     */
    RespRecurrence userRegister(UserRegisterDTO userRegisterDTO);
}
