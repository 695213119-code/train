package com.train.commonservice.constant.user;

/**
 * 用户服务常量类
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
public class UserConstant {


    /**
     * 用户token保存到redis的key
     */
    public static final String USER_TOKEN_REDIS_KEY = "user_token_redis_key";

    /**
     * 用户管理端登录方法名称
     */
    public static final String USER_MANAGEMANT_LOGIN_METHOD_NAME = "userManagementLogin";

    /**
     * 登录平台-pc
     */
    public static final Integer PLATFORM_PC = 1;

    /**
     * 第三方平台类型-微信
     */
    public static final Integer THIRD_PLATFORM_TYPE_WECHAT = 2;

    /**
     * 第三方平台类型-qq
     */
    public static final Integer THIRD_PLATFORM_TYPE_QQ = 1;

}
