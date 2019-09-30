package com.train.usercenterservice.service;

import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.usercenterservice.dto.AddAdministratorsDTO;
import com.train.usercenterservice.dto.QueryUserManagementLoginDTO;
import com.train.usercenterservice.dto.QueryUserTabulationDTO;
import org.springframework.validation.BindingResult;

/**
 * 用户中心->用户模块接口类
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
public interface IUserCenterService {


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
    RespRecurrence userManagementLogin(QueryUserManagementLoginDTO userManagementLoginDTO, BindingResult bindingResult);

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

    /**
     * 获取用户列表
     *
     * @param queryUserTabulationDTO 列表查询条件DTO
     * @param bindingResult          BindingResult
     * @return RespPageRecurrence
     */
    RespPageRecurrence queryUserTabulation(QueryUserTabulationDTO queryUserTabulationDTO, BindingResult bindingResult);


    /**
     * 添加管理员
     *
     * @param administrators 添加管理员参数类
     * @param bindingResult  BindingResult
     * @return RespRecurrence
     */
    RespRecurrence addAdministrators(AddAdministratorsDTO administrators, BindingResult bindingResult);

    /**
     * 重置用户密码
     *
     * @param userId 用户id
     * @return RespRecurrence
     */
    RespRecurrence resetPassword(Long userId);
}
