package com.train.gatewayservice.remote.core.fallback;

import com.train.gatewayservice.remote.core.RemoteCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 核心服务-Fallback类
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
@Component
public class RemoteCoreServiceImpl implements RemoteCoreService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RemoteCoreServiceImpl.class);

    @Override
    public boolean checkPathNeedsTokenServiceInvocation(String path) {
        LOGGER.error("调用服务[core-service]接口[checkPathNeedsToken]失败");
        return false;
    }
}
