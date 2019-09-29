package com.train.usercenterservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 用户登录日志查询DTO
 *
 * @author zhangLei
 * @serial 219/09/12
 */
@Data
public class QueryUserLoginLogTabulationDTO {

    @ApiModelProperty("页码")
    @Min(value = 1,message = "页码必须大于1")
    private Integer page;

    @ApiModelProperty("每页条数")
    @Min(value = 1,message = "每页条数必须大于1")
    private Integer limit;

    @ApiModelProperty("登录时间-开始")
    private String timeStart;

    @ApiModelProperty("登录时间-结束")
    private String timeEnd;

    @ApiModelProperty("登录平台（1-pc 2-app 3-小程序）")
    private Integer platform;

    @ApiModelProperty("用户手机号/用户名")
    private String userKey;

    @ApiModelProperty("IP地址")
    private String ipAddress;

}
