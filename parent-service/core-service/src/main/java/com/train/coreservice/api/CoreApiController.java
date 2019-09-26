package com.train.coreservice.api;

import com.train.coreservice.service.ICoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 核心API
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
@RestController
@RequestMapping("/api/core")
@Api(tags = "核心API", hidden = true)
public class CoreApiController {

    private final ICoreService coreService;

    @Autowired
    public CoreApiController(ICoreService coreService) {
        this.coreService = coreService;
    }

    @ApiOperation("校验请求接口是否需要token[服务调用]")
    @GetMapping("/checkPathNeedsTokenServiceInvocation")
    public boolean checkPathNeedsTokenServiceInvocation(@ApiParam(value = "接口路径", required = true) @RequestParam String path) {
        return coreService.checkPathNeedsTokenServiceInvocation(path);
    }

}
