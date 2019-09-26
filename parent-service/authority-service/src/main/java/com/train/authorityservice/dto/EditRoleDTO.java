package com.train.authorityservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 修改角色DTO
 *
 * @author zhangLei
 * @serial 2019/9/26
 */
@Data
public class EditRoleDTO {


    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    @NotNull(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色职责")
    @NotNull(message = "角色名称不能为空")
    private String roleDuty;
}
