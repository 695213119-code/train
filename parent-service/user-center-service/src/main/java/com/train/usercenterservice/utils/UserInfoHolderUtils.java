package com.train.usercenterservice.utils;

import com.alibaba.fastjson.JSONObject;
import com.train.commonservice.constant.CommonConstant;
import com.train.commonservice.exception.BusinessException;
import com.train.entityservice.entity.user.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户工具类
 *
 * @author zhangLei
 * @serial 2019/09/20
 */
@Component
public class UserInfoHolderUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserInfoHolderUtils.class);

    private final RedisUtils redisUtils;
    private final HttpServletRequest request;

    @Autowired
    public UserInfoHolderUtils(RedisUtils redisUtils, HttpServletRequest request) {
        this.redisUtils = redisUtils;
        this.request = request;
    }

    /**
     * 获取用户
     *
     * @return User
     */
    public User getUser() {
        String accessToken = CommonConstant.ACCESS_TOKEN;
        String token = request.getHeader(accessToken);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(accessToken);
        }
        String userJsonString = redisUtils.get(token);
        User user = JSONObject.parseObject(userJsonString, User.class);
        if (null == user || null == user.getId()) {
            try {
                throw new BusinessException("获取用户信息失败");
            } catch (BusinessException e) {
                LOGGER.error("根据token获取用户信息失败,原因:{},参数:{}", e.getMessage(), token);
            }
        }
        return user;
    }


    /**
     * 获取用户id
     *
     * @return Long
     */
    public Long getUserId() {
        return getUser().getId();
    }


}
