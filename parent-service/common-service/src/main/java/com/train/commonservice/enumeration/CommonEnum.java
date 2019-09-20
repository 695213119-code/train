package com.train.commonservice.enumeration;

/**
 * 公共枚举类
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
public enum CommonEnum {

    /**
     * 成功返回的状态码
     */
    SUCCESS_CODE("200", "成功"),

    /**
     * 失败返回的状态码
     */
    FAIL_CODE("500", "失败"),

    /**
     * token为空
     */
    TOKEN_ISNULL("401", "token不存在或已过期"),

    /**
     * 非法的token
     */
    TOKEN_UNLAWFUL("402", "非法的token"),

    /**
     * 非法的参数
     */
    INVALID_PARAMETER("403", "非法的参数");


    private String code;
    private String message;

    CommonEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
