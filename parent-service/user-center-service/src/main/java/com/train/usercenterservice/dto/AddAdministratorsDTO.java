package com.train.usercenterservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加管理员DTO
 *
 * @author zhangLei
 * @serial 2019/9/30
 */
@Data
public class AddAdministratorsDTO {

    @ApiModelProperty("手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty("角色id")
    @NotNull(message = "角色不能为空")
    private String roleId;
}
