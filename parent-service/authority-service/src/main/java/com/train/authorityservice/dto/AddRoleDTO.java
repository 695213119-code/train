package com.train.authorityservice.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 添加角色 DTO
 *
 * @author zhangLei
 * @serial 2019/09/21
 */
@Data
public class AddRoleDTO {

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色职责")
    @NotBlank(message = "角色名称不能为空")
    private String roleDuty;
    
}
