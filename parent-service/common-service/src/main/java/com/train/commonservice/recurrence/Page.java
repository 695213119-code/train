package com.train.commonservice.recurrence;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义分页参数类
 *
 * @author zhangLei
 * @serial 2019/09/22
 */
@Data
public class Page {

    @ApiModelProperty("总数量")
    private long total;

    @ApiModelProperty("每页显示条数")
    private long size;

    @ApiModelProperty("当前页码")
    private int current;

    @ApiModelProperty("总页数")
    private long totalPages;

}
