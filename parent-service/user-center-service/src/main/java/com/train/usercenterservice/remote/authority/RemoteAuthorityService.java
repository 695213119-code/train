package com.train.usercenterservice.remote.authority;

import com.train.entityservice.entity.authority.Role;
import com.train.entityservice.entity.vo.UserAuthorityVO;
import com.train.usercenterservice.remote.authority.fallback.RemoteAuthorityServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 权限调用类
 *
 * @author zhangLei
 * @serial 2019/09/24
 */
@FeignClient(value = "authority-service", fallback = RemoteAuthorityServiceImpl.class)
@Service
public interface RemoteAuthorityService {

    String API_AUTHORITY = "/api/authority";

    /**
     * 查询用户权限
     *
     * @param userId 用户id
     * @return List
     */
    @GetMapping(API_AUTHORITY + "/getUserAuthorityServiceInvocation")
    List<UserAuthorityVO> getUserAuthorityServiceInvocation(@RequestParam(value = "userId") Long userId);

    /**
     * 根据角色id查询角色信息
     *
     * @param roleId 角色id
     * @return Role
     */
    @GetMapping(API_AUTHORITY + "/getRoleServiceInvocation")
    Role getRoleServiceInvocation(@RequestParam(value = "roleId") Long roleId);

}
