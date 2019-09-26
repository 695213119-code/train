package com.train.usercenterservice.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户核心数据VO
 *
 * @author zhangLei
 * @serial 2019/9/26
 */
@Data
public class UserCoreVO {

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户角色名称")
    private String roleName;

    @ApiModelProperty(value = "用户名(实名制)")
    private String userName;

    @ApiModelProperty(value = "身份证号码(实名制)")
    private String idCard;

    @ApiModelProperty(value = "用户身份证号码链接地址")
    private String cardUrl;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
