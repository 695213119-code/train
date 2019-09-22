package com.train.usercenterservice.service;

import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.usercenterservice.dto.UserLoginLogQueryDTO;
import org.springframework.validation.BindingResult;

/**
 * 用户登录日志接口类
 *
 * @author zhangLei
 * @serial 2019/09/12
 */
public interface IUserLoginLogService {

    /**
     * 获取用户登录日志
     *
     * @param userLoginLogQueryDTO 条件参数
     * @param bindingResult        BindingResult
     * @return RespPageRecurrence
     */
    RespPageRecurrence queryUserLoginLog(UserLoginLogQueryDTO userLoginLogQueryDTO, BindingResult bindingResult);
}
