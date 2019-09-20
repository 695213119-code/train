package com.train.authorityservice.api;

import com.train.authorityservice.service.IAuthorityService;
import com.train.entityservice.entity.vo.UserAuthorityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限模块API
 *
 * @author zhangLei
 * @serial 2019/09/21
 */
@RestController
@RequestMapping("/api/authority")
@Api(tags = "权限api", hidden = true)
public class AuthorityApiController {

    private final IAuthorityService authorityService;

    @Autowired
    public AuthorityApiController(IAuthorityService authorityService) {
        this.authorityService = authorityService;
    }


    @GetMapping("/getUserAuthority")
    @ApiOperation(value = "查询用户权限[服务调用API]")
    public List<UserAuthorityVO> getUserAuthority(@ApiParam(value = "用户id", required = true) @RequestParam Long userId) {
        return authorityService.getUserAuthority(userId);
    }






}
