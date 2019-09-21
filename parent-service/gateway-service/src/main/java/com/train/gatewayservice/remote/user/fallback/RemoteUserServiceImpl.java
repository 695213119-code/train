package com.train.gatewayservice.remote.user.fallback;

import com.train.gatewayservice.remote.core.fallback.RemoteCoreServiceImpl;
import com.train.gatewayservice.remote.user.RemoteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务-Fallback类
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
@Component
public class RemoteUserServiceImpl implements RemoteUserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RemoteUserServiceImpl.class);

    @Override
    public boolean checkUserTokenServiceInvocation(String userToken) {
        LOGGER.error("调用服务[user-center-service]接口[checkUserToken]失败");
        return false;
    }
}
