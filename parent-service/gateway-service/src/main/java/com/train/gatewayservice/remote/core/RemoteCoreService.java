package com.train.gatewayservice.remote.core;

import com.train.gatewayservice.remote.core.fallback.RemoteCoreServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 核心服务-调用类
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
@FeignClient(value = "core-service", fallback = RemoteCoreServiceImpl.class)
@Service
public interface RemoteCoreService {

    String CORE_API = "/api/core";

    /**
     * 校验请求接口是否需要token
     *
     * @param path 接口路径
     * @return boolean
     */
    @GetMapping(CORE_API + "/checkPathNeedsTokenServiceInvocation")
    boolean checkPathNeedsTokenServiceInvocation(@RequestParam(value = "path") String path);
}
