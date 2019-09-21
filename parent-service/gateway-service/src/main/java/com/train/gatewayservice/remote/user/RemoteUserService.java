package com.train.gatewayservice.remote.user;


import com.train.gatewayservice.remote.user.fallback.RemoteUserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务-调用类
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
@FeignClient(value = "user-center-service", fallback = RemoteUserServiceImpl.class)
@Service
public interface RemoteUserService {

    String USER_API = "/api/user-center";

    /**
     * 检验用户token是否合法
     *
     * @param userToken 用户token
     * @return boolean
     */
    @GetMapping(USER_API + "/checkUserTokenServiceInvocation")
    boolean checkUserTokenServiceInvocation(@RequestParam(value = "userToken") String userToken);

}
