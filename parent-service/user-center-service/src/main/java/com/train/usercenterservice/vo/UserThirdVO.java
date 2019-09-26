package com.train.usercenterservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户第三方数据VO
 *
 * @author zhangLei
 * @serial 2019/9/26
 */
@Data
public class UserThirdVO {

    @ApiModelProperty(value = "平台类型(1-qq 2-微信)")
    private Integer platform;

    @ApiModelProperty(value = "第三方平台的用户标识")
    private String unionId;

    @ApiModelProperty(value = "第三方用户性别(0-未知 1-男 2女)")
    private Integer gender;

    @ApiModelProperty(value = "第三方用户昵称")
    private String nickName;

    @ApiModelProperty(value = "第三方用户年龄")
    private Integer age;

    @ApiModelProperty(value = "第三方用户头像")
    private String avatar;
}
