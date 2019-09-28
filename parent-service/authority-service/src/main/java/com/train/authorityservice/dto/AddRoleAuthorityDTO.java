package com.train.authorityservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 角色赋权DTO
 *
 * @author zhangLei
 * @serial 2019/9/27
 */
@Data
public class AddRoleAuthorityDTO {


    @ApiModelProperty("角色id")
    @NotNull
    private Long roleId;

    @ApiModelProperty("权限id 数组")
    private Long[] roleAuthority;
}
