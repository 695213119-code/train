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
    TOKEN_ISNULL("401", "token不存在或已过期");


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
