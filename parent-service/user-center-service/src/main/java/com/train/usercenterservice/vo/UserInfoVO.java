package com.train.usercenterservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户信息返回类
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
@Data
public class UserInfoVO {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户性别(1-男 2-女)")
    private Integer gender;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

}
