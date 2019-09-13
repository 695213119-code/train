package com.train.commonservice.recurrence;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 核心数据返回类(分页)
 * 统一返回成功状态码(code)为200
 * 统一提示消息为message
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
@Data
public class RespPageRecurrence<T> implements Serializable {

    @ApiModelProperty("状态码, 200表示成功")
    private String code;

    @ApiModelProperty("提示消息")
    private String message;

    @ApiModelProperty("总数量")
    private long total;

    @ApiModelProperty("总页数")
    private long totalPages;

    @ApiModelProperty("当前页码")
    private int currentPage;

    @ApiModelProperty("当前数量")
    private long currentCount;

    @ApiModelProperty("返回数据")
    protected T data;

    public RespPageRecurrence() {
    }

    //TODO 未完成


}
