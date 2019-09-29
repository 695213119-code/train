package com.train.coreservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 编辑字典DTO
 *
 * @author zhangLei
 * @serial 2019/09/18
 */
@Data
public class EditDictionariesDTO {

    @ApiModelProperty(value = "主键")
    @NotNull(message = "字典id不能为空")
    private Long id;

    @ApiModelProperty(value = "字典的val")
    @NotNull(message = "字典val不能为空")
    private String dicValue;

    @ApiModelProperty(value = "备注")
    @NotNull(message = "字典备注不能为空")
    private String remarks;
}
