package com.train.authorityservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 权限添加-DTO
 *
 * @author zhangLei
 * @serial 2019/9/25
 */
@Data
public class AuthorityAddDTO {

    @ApiModelProperty(value = "权限名称")
    @NotNull(message = "权限名称不能为空")
    private String jurName;

    @ApiModelProperty(value = "权限唯一标识")
    @NotNull(message = "权限唯一标识不能为空")
    private String identification;

    @ApiModelProperty(value = "父级id（默认0为顶级）")
    @NotNull(message = "角父级id不能为空")
    private Long parentId;

}
