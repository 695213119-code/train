package com.train.usercenterservice.api;


import com.train.commonservice.recurrence.RespRecurrence;
import com.train.usercenterservice.dto.UserLoginDTO;
import com.train.usercenterservice.dto.UserManagementLoginDTO;
import com.train.usercenterservice.dto.UserRegisterDTO;
import com.train.usercenterservice.inter.Intercept;
import com.train.usercenterservice.service.IUserCenterService;
import com.train.usercenterservice.vo.UserInfoVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户模块API
 *
 * @author zhangLei
 * @serial 2019/09/16
 */
@RestController
@RequestMapping("/api/user-center")
@Api(tags = "用户中心api", hidden = true)
public class UserCenterApiController {

    private final IUserCenterService userCenterService;

    @Autowired
    public UserCenterApiController(IUserCenterService userCenterService) {
        this.userCenterService = userCenterService;
    }

    @GetMapping("/checkUserToken")
    @ApiOperation(value = "校验用户token[服务调用API]")
    public boolean checkUserToken(@ApiParam(value = "用户token", required = true) @RequestParam String userToken) {
        return userCenterService.checkUserToken(userToken);
    }

    @PostMapping("/userManagementLogin")
    @ApiOperation(value = "用户管理端登录")
    @Intercept
    public RespRecurrence userManagementLogin(@ApiParam(value = "用户管理端登录DTO", required = true) @RequestBody @Validated UserManagementLoginDTO userManagementLoginDTO,
                                              BindingResult bindingResult) {
        return userCenterService.userManagementLogin(userManagementLoginDTO, bindingResult);
    }

    @PostMapping("/getUserDetails")
    @ApiOperation(value = "获取用户详情",response = UserInfoVO.class)
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", value = "token标记", required = true) })
    public RespRecurrence getUserDetails(@ApiParam(value = "用户登录方式 1-手机号 2-微信 3-扣扣", required = true) @RequestParam String key) {
        return userCenterService.getUserDetails(key);
    }


    @PostMapping("/userRegister")
    @ApiOperation(value = "用户注册")
    public RespRecurrence userRegister(@ApiParam(value = "用户注册参数类", required = true) @RequestBody UserRegisterDTO userRegisterDTO) {
        return userCenterService.userRegister(userRegisterDTO);
    }


}


