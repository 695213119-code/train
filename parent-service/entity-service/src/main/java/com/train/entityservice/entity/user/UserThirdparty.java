package com.train.entityservice.entity.user;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * <p>
 * 用户第三方信息表
 * </p>
 *
 * @author Administrator
 * @since 2019-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("train_user_thirdparty")
@ApiModel(value = "用户第三方信息表")
public class UserThirdparty extends Model<UserThirdparty> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "平台类型(1-qq 2-微信)")
    private Integer platform;

    @ApiModelProperty(value = "第三方平台的用户标识")
    @TableField("union_id")
    private String unionId;

    @ApiModelProperty(value = "第三方用户性别(0-未知 1-男 2女)")
    private Integer gender;

    @ApiModelProperty(value = "第三方用户昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "第三方用户年龄")
    private Integer age;

    @ApiModelProperty(value = "第三方用户头像")
    private String avatar;

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