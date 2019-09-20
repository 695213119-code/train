package com.train.authorityservice.service;

import com.train.entityservice.entity.vo.UserAuthorityVO;

import java.util.List;
import java.util.Map;

/**
 * 权限模块接口
 *
 * @author zhangLei
 * @serial 2019/09/21
 */
public interface IAuthorityService {


    /**
     * 查询用户权限
     *
     * @param userId 用户id
     * @return List
     */
    List<UserAuthorityVO> getUserAuthority(Long userId);
}
