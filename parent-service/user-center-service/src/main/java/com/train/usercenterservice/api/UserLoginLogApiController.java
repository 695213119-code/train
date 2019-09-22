package com.train.usercenterservice.api;

import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.usercenterservice.dto.UserLoginLogQueryDTO;
import com.train.usercenterservice.service.IUserLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录日志API
 *
 * @author zhangLei
 * @serial 2019/09/16
 */
@RestController
@RequestMapping("/api/user-loginLog")
@Api(tags = "用户登录日志API", hidden = true)
public class UserLoginLogApiController {

    private final IUserLoginLogService userLoginLogService;

    @Autowired
    public UserLoginLogApiController(IUserLoginLogService userLoginLogService) {
        this.userLoginLogService = userLoginLogService;
    }


    @PostMapping("/queryUserLoginLog")
    @ApiOperation(value = "获取用户登录日志")
    public RespPageRecurrence queryUserLoginLog(@ApiParam(value = "条件参数", required = true) @RequestBody @Validated UserLoginLogQueryDTO userLoginLogQueryDTO,
                                                BindingResult bindingResult) {
        return userLoginLogService.queryUserLoginLog(userLoginLogQueryDTO, bindingResult);
    }

}
