package com.train.usercenterservice.api;


import com.train.commonservice.recurrence.RespRecurrence;
import com.train.usercenterservice.dto.UserLoginDTO;
import com.train.usercenterservice.dto.UserRegisterDTO;
import com.train.usercenterservice.service.IUserCenterService;
import com.train.usercenterservice.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/userRegister")
    @ApiOperation(value = "用户注册")
    public RespRecurrence userRegister(@ApiParam(value = "用户注册参数类", required = true) @RequestBody UserRegisterDTO userRegisterDTO) {
        return userCenterService.userRegister(userRegisterDTO);
    }


    @PostMapping("/userLogin")
    @ApiOperation(value = "用户登录--pc")
    public RespRecurrence<UserInfoVO> userLogin(@ApiParam(value = "用户登录参数类", required = true) @RequestBody UserLoginDTO userLoginDTO) {
        return userCenterService.userLogin(userLoginDTO);
    }


    @GetMapping("/checkUserToken")
    @ApiOperation(value = "校验用户token")
    public boolean checkUserToken(@ApiParam(value = "用户token", required = true) @RequestParam String userToken) {
        return userCenterService.checkUserToken(userToken);
    }


}


