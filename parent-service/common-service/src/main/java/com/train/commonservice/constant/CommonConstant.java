package com.train.commonservice.constant;

/**
 * 系统公共常量类
 *
 * @author zhangLei
 * @serial 2019/09/18
 */
public class CommonConstant {

    /**
     * 数据库删除标识
     */
    public static final String SQL_DELETE_SIGN = "is_deleted";

    /**
     * 数据库删除标识 -未删除
     */
    public static final Integer SQL_DELETE_SIGN_NOT = 2;

    /**
     * 数据库删除标识 -已经删除
     */
    public static final Integer SQL_DELETE_SIGN_ALREADY = 1;

    /**
     * 常用切割符号 [,]
     */
    public static final String COMMA = ",";

    /**
     * 前端请求的token标识
     */
    public static final String ACCESS_TOKEN = "access_token";

}
