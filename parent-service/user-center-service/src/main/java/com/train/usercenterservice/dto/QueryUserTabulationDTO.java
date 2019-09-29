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
    private Integer page;

    @ApiModelProperty("每页条数")
    @Min(value = 1, message = "每页条数必须大于1")
    private Integer limit;

    @ApiModelProperty("手机号/用户名/身份证号码")
    private String comprehensive;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("是否实名 1-是 2-否")
    private Integer isReal;

    @ApiModelProperty("用户创建时间-开始")
    private String crateStartTime;

    @ApiModelProperty("用户创建时间-结束")
    private String crateEndTime;



}
