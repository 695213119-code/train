package com.train.usercenterservice.aspect;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.train.commonservice.constant.CommonConstant;
import com.train.commonservice.constant.SqlConstant;
import com.train.commonservice.constant.user.UserConstant;
import com.train.entityservice.entity.user.LoginLog;
import com.train.entityservice.entity.user.User;
import com.train.commonservice.utils.IpUtils;
import com.train.usercenterservice.dto.UserLoginDTO;
import com.train.usercenterservice.user.service.ILoginLogService;
import com.train.usercenterservice.user.service.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户服务切面类
 *
 * @author zhangLei
 * @serial 2019/09/18
 */
@Aspect
@Component
public class UserAspect {

    private final IUserService userService;
    private final ILoginLogService loginLogService;
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public UserAspect(IUserService userService, ILoginLogService loginLogService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.loginLogService = loginLogService;
        this.httpServletRequest = httpServletRequest;
    }


    @Pointcut("@annotation(com.train.usercenterservice.inter.Intercept)")
    private void userPointcut() {

    }


    @After("userPointcut()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        if (UserConstant.USER_LOGIN_METHOD_NAME.equals(methodName)) {
            UserLoginDTO userLoginDTO = (UserLoginDTO) args[0];
            User user = userService.selectOne(new EntityWrapper<User>().eq(SqlConstant.SQL_FIELD_PHONE, userLoginDTO.getPhone()).
                    eq(CommonConstant.SQL_DELETE_SIGN, CommonConstant.SQL_DELETE_SIGN_NOT));
            if (null != user && null != user.getId()) {
                LoginLog loginLog = new LoginLog();
                String ipAddr = IpUtils.getIpAddr(httpServletRequest);
                loginLog.setUserId(user.getId());
                loginLog.setLoginTime(new Date());
                loginLog.setIpAddress(ipAddr);
                loginLog.setPlatform(UserConstant.PLATFORM_PC);
                loginLogService.insert(loginLog);
            }


        }
    }

}
