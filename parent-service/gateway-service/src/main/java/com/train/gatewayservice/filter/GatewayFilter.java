package com.train.gatewayservice.filter;


import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.train.commonservice.enumeration.CommonEnum;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.gatewayservice.remote.core.RemoteCoreService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 网关拦截器
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
@Component
public class GatewayFilter extends ZuulFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(GatewayFilter.class);

    private final RemoteCoreService remoteCoreService;

    @Autowired
    public GatewayFilter(RemoteCoreService remoteCoreService) {
        this.remoteCoreService = remoteCoreService;
    }

    /**
     * 过滤器类型，前置过滤器
     *
     * @return String
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }


    /**
     * 过滤器顺序，越小越先执行
     *
     * @return int
     */
    @Override
    public int filterOrder() {
        return 4;
    }

    /**
     * 过滤器是否生效 这里所有的服务接口都进行拦截
     *
     * @return boolean
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 此方法为网关拦截的具体实现方法
     *
     * @return Object
     */
    @Override
    public Object run() {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestUrl = request.getRequestURI();

        LOGGER.info("请求的URL====>>{}", requestUrl);

        //url拦截
        boolean pathNeedsToken = remoteCoreService.checkPathNeedsToken(requestUrl);
        if (pathNeedsToken) {
            //用户鉴权
            String token = getToken(request);
            if (StringUtils.isEmpty(token)) {
                failureRequest(currentContext);
                return null;
            }


        }

        return null;
    }


    /**
     * 获取token
     *
     * @param request request对象
     * @return String
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("access_token");
        }
        return token;
    }

    /**
     * 失败的请求
     *
     * @param currentContext currentContext对象
     */
    private void failureRequest(RequestContext currentContext) {
        currentContext.set("isSuccess", false);
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseBody(JSONObject.toJSON(new RespRecurrence().failure(CommonEnum.TOKEN_ISNULL.getCode(), CommonEnum.TOKEN_ISNULL.getMessage())).toString());
        currentContext.getResponse().setContentType("application/json;charset=UTF-8");
    }


}
