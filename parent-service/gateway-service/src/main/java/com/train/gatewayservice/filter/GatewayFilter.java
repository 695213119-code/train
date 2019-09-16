package com.train.gatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * 网关拦截器
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
public class GatewayFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        //此方法为网关拦截的具体实现方法

        //TODO 获取进入网关的方法参数

        //TODO 校验接口是否需要验证token

        //TODO 验证token是否合法

        //TODO 用户鉴权

        return null;
    }
}
