package com.train.entityservice.entity.user;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * <p>
 * 登录日志表
 * </p>
 *
 * @author Administrator
 * @since 2019-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("train_login_log")
@ApiModel(value = "登录日志表")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "登录平台（1-pc 2-app 3-小程序）")
    private Integer platform;

    @ApiModelProperty(value = "登录IP地址")
    @TableField("ip_address")
    private String ipAddress;

    @ApiModelProperty(value = "登录时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除 1-是 2-否")
    @TableField("is_deleted")
    private Integer isDeleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}