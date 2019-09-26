package com.train.usercenterservice.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户列表-VO
 *
 * @author zhangLei
 * @serial 2019/9/26
 */
@Data
public class UserTabulationVO {

    @ApiModelProperty("用户id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("用户核心数据")
    private UserCoreVO userCore;

    @ApiModelProperty("用户附属数据")
    private UserSubsidiaryVO userSubsidiary;

    @ApiModelProperty("用户第三方数据集合")
    private List<UserThirdVO> userThirds;

    @ApiModelProperty("用户临时权限数据集合")
    private List<UserTemAuthVO> userTemAuths;
}
