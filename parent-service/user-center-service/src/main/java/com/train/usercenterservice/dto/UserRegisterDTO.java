package com.train.usercenterservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户注册DTO
 *
 * @author zhangLei
 * @serial 219/09/14
 */
@Data
public class UserRegisterDTO {

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;

}
