package com.train.usercenterservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录DTO--PC
 *
 * @author zhangLei
 * @serial 219/09/17
 */
@Data
public class UserLoginDTO {

    @ApiModelProperty("登录类型 1-手机号密码 2-手机验证码 3-微信 4-扣扣")
    private String type;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;

    @ApiModelProperty("第三方的唯一标识")
    private String onlyKey;

}
