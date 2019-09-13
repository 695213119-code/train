package com.train.commonservice.recurrence;

import com.train.commonservice.enumeration.CommonEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 核心数据返回类
 * 统一返回成功状态码(code)为200
 * 统一提示消息为message
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
@Data
public class RespRecurrence<T> implements Serializable {

    private static final long serialVersionUID = 2169140102785610954L;

    @ApiModelProperty("状态码, 200表示成功")
    private String code;

    @ApiModelProperty("提示消息")
    private String message;

    @ApiModelProperty("返回数据")
    protected T data;

    public RespRecurrence() {
    }

    public RespRecurrence<T> success() {
        this.code = CommonEnum.SUCCESS_CODE.getCode();
        this.message = CommonEnum.SUCCESS_CODE.getMessage();
        return this;
    }


    public RespRecurrence<T> success(T data) {
        this.data = data;
        this.code = CommonEnum.SUCCESS_CODE.getCode();
        this.message = CommonEnum.SUCCESS_CODE.getMessage();
        return this;
    }

    public RespRecurrence<T> success(T data, String message) {
        this.code = CommonEnum.SUCCESS_CODE.getCode();
        this.message = message;
        this.data = data;
        return this;
    }

    public RespRecurrence<T> failure() {
        this.code = CommonEnum.FAIL_CODE.getCode();
        this.message = CommonEnum.FAIL_CODE.getMessage();
        return this;
    }


    public RespRecurrence<T> failure(String message) {
        this.code = CommonEnum.FAIL_CODE.getCode();
        this.message = message;
        return this;
    }

    public RespRecurrence<T> failure(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "RespRecurrence{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
