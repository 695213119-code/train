package com.train.authorityservice.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色权限VO
 *
 * @author zhangLei
 * @serial 2019/9/25
 */
@Data
public class AuthorityVO implements Cloneable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String jurName;

    @ApiModelProperty(value = "权限唯一标识")
    @JsonIgnore
    private String identification;

    @ApiModelProperty(value = "父级id（默认0为顶级）")
    @JsonIgnore
    private Long parentId;

    @ApiModelProperty(value = "角色是否存在该权限(1-不存在 2-存在) 默认1")
    private Integer itExist = 1;

    @ApiModelProperty(value = "子级权限")
    private List<AuthorityVO> roleAuthorityChildren;

    /**
     * 重写clone方法
     */
    @Override
    public AuthorityVO clone() throws CloneNotSupportedException {
        return (AuthorityVO) super.clone();
    }


}
