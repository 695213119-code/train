package com.train.commonservice.recurrence;

import com.train.commonservice.enumeration.CommonEnum;
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

    @ApiModelProperty("每页显示条数")
    private long size;

    @ApiModelProperty("当前页码")
    private int current;

    @ApiModelProperty("总页数")
    private long totalPages;

    @ApiModelProperty("返回数据")
    protected T data;

    public RespPageRecurrence() {
    }




    public RespPageRecurrence<T> success(T data, Page page) {
        this.data = data;
        this.code = CommonEnum.SUCCESS_CODE.getCode();
        this.message = CommonEnum.SUCCESS_CODE.getMessage();
        this.total = page.getTotal();
        this.totalPages = page.getTotalPages();
        this.current = page.getCurrent();
        this.size = page.getSize();
        return this;
    }


    public RespPageRecurrence<T> failure(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }


}
