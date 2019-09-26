package com.train.usercenterservice.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户附属数据VO
 *
 * @author zhangLei
 * @serial 2019/9/26
 */
@Data
public class UserSubsidiaryVO {

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户性别(1-男 2-女)")
    private Integer gender;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;

    @ApiModelProperty(value = "用户生日(农历)")
    private String birthdayLunar;

    @ApiModelProperty(value = "用户生日(公历,下一次生日时间)")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthdayGregorian;

}
