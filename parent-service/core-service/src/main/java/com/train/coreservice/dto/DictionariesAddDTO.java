package com.train.coreservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 添加字典DTO
 *
 * @author zhangLei
 * @serial 2019/09/18
 */
@Data
public class DictionariesAddDTO {

    @ApiModelProperty(value = "字典的key")
    private String key;

    @ApiModelProperty(value = "字典的val")
    private String value;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @Override
    public String toString() {
        return "DictionariesAddDTO{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
