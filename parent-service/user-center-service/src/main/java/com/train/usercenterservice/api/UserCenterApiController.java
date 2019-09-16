package com.train.usercenterservice.api;


import com.train.commonservice.recurrence.RespRecurrence;
import com.train.usercenterservice.dto.UserRegisterDTO;
import com.train.usercenterservice.service.IUserCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}


