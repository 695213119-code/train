package com.train.usercenterservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * token返回类-VO
 *
 * @author zhangLei
 * @serial 2019/09/20
 */
@Data
public class TokenVO {

    public TokenVO(String token) {
        this.token = token;
    }

    @ApiModelProperty("用户的token")
    private String token;
}
