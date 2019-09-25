package com.train.coreservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加字典DTO
 *
 * @author zhangLei
 * @serial 2019/09/18
 */
@Data
public class DictionariesAddDTO {

    @ApiModelProperty(value = "字典的key")
    @NotNull(message = "字典的key不能为空")
    private String dicKey;

    @ApiModelProperty(value = "字典的val")
    @NotNull(message = "字典的val不能为空")
    private String dicValue;

    @ApiModelProperty(value = "备注")
    @NotNull(message = "备注不能为空")
    private String remarks;

}
