package com.train.coreservice.api;

import com.train.commonservice.recurrence.RespRecurrence;
import com.train.coreservice.dto.FiltrateInterfaceDTO;
import com.train.coreservice.service.IFilterInterfaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 过滤接口API
 *
 * @author zhangLei
 * @serial 2019/9/25
 */
@RestController
@RequestMapping("/api/filterInterface")
@Api(tags = "过滤接口API", hidden = true)
public class FilterInterfaceApiController {

    private final IFilterInterfaceService filterInterfaceService;


    @Autowired
    public FilterInterfaceApiController(IFilterInterfaceService filterInterfaceService) {
        this.filterInterfaceService = filterInterfaceService;
    }


    @ApiOperation("添加过滤接口")
    @PostMapping("/addFiltrateInterface")
    public RespRecurrence addFiltrateInterface(@ApiParam(value = "过滤接口参数类", required = true) @RequestBody FiltrateInterfaceDTO filtrateInterfaceDTO) {
        return filterInterfaceService.addFiltrateInterface(filtrateInterfaceDTO);
    }
}
