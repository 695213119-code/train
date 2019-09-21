package com.train.usercenterservice.remote.authority.fallback;

import com.train.entityservice.entity.authority.Role;
import com.train.entityservice.entity.vo.UserAuthorityVO;
import com.train.usercenterservice.remote.authority.RemoteAuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 权限调用fallback类
 *
 * @author zhangLei
 * @serial 2019/09/24
 */
@Component
public class RemoteAuthorityServiceImpl implements RemoteAuthorityService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RemoteAuthorityServiceImpl.class);


    @Override
    public List<UserAuthorityVO> getUserAuthorityServiceInvocation(Long userId) {
        LOGGER.error("调用服务[authority-service]接口[getUserAuthorityServiceInvocation]失败");
        return Collections.emptyList();
    }

    @Override
    public Role getRoleServiceInvocation(Long roleId) {
        LOGGER.error("调用服务[authority-service]接口[getRoleServiceInvocation]失败");
        return null;
    }
}
