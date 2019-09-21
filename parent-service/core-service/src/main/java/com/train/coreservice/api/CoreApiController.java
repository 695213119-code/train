package com.train.coreservice.api;

import com.train.commonservice.recurrence.RespRecurrence;
import com.train.coreservice.dto.DictionariesAddDTO;
import com.train.coreservice.dto.FiltrateInterfaceDTO;
import com.train.coreservice.service.ICoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("添加过滤接口")
    @PutMapping("/addFiltrateInterface")
    public RespRecurrence addFiltrateInterface(@ApiParam(value = "过滤接口参数类", required = true) @RequestBody FiltrateInterfaceDTO filtrateInterfaceDTO) {
        return coreService.addFiltrateInterface(filtrateInterfaceDTO);
    }

    @ApiOperation("添加字典")
    @PutMapping("/addDictionaries")
    public RespRecurrence addDictionaries(@ApiParam(value = "字典参数类", required = true) @RequestBody DictionariesAddDTO dictionariesAddDTO) {
        return coreService.addDictionaries(dictionariesAddDTO);
    }

}
