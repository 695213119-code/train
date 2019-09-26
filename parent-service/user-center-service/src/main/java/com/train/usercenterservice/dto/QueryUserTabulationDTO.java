package com.train.usercenterservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 用户列表查询DTO
 *
 * @author zhangLei
 * @serial 2019/9/26
 */
@Data
public class QueryUserTabulationDTO {

    @ApiModelProperty("页码")
    @Min(value = 1, message = "页码必须大于1")
    private int page;

    @ApiModelProperty("每页条数")
    @Min(value = 1, message = "每页条数必须大于1")
    private int limit;
}
