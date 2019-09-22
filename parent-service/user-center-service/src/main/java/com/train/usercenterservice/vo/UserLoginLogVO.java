package com.train.usercenterservice.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户登录日志返回类-VO
 *
 * @author zhangLei
 * @serial 2019/09/22
 */
@Data
public class UserLoginLogVO {

    @ApiModelProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户手机号")
    private String phone;

    @ApiModelProperty(value = "登录平台（1-pc 2-app 3-小程序）")
    private Integer platform;

    @ApiModelProperty(value = "登录IP地址")
    private String ipAddress;

    @ApiModelProperty(value = "登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;


}
