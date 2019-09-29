package com.train.usercenterservice.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户管理端登录类-DTO
 *
 * @author zhangLei
 * @serial 2019/09/20
 */
@Data
public class QueryUserManagementLoginDTO {

    @ApiModelProperty("手机号")
    @NotNull(message = "用户手机号码不能为空")
    @JsonAlias("username")
    private String phone;

    @ApiModelProperty("密码")
    @NotNull(message = "用户密码不能为空")
    private String password;

}
