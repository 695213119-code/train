package com.train.coreservice.service;

/**
 * 核心API接口类
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
public interface ICoreService {
    /**
     * 校验请求接口是否需要token
     *
     * @param path 接口路径
     * @return boolean
     */
    boolean checkPathNeedsTokenServiceInvocation(String path);



}
