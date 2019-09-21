package com.train.usercenterservice.service;

import com.train.commonservice.recurrence.RespRecurrence;
import com.train.usercenterservice.dto.UserManagementLoginDTO;
import com.train.usercenterservice.dto.UserRegisterDTO;
import org.springframework.validation.BindingResult;

/**
 * 用户中心->用户模块接口类
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


    /**
     * 校验用户token
     *
     * @param userToken 用户token
     * @return boolean
     */
    boolean checkUserTokenServiceInvocation(String userToken);

    /**
     * 用户管理端登录
     *
     * @param userManagementLoginDTO 用户管理端登录DTO
     * @param bindingResult          校验api
     * @return RespRecurrence
     */
    RespRecurrence userManagementLogin(UserManagementLoginDTO userManagementLoginDTO, BindingResult bindingResult);

    /**
     * 获取用户详情
     *
     * @param key 用户登录方式 1-手机号 2-微信 3-扣扣
     * @return RespRecurrence
     */
    RespRecurrence getUserDetails(String key);

    /**
     * 用户登出
     *
     * @return RespRecurrence
     */
    RespRecurrence userLogOut();
}
