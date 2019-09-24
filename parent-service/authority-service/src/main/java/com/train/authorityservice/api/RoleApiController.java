package com.train.authorityservice.api;

import com.train.authorityservice.dto.AddRoleDTO;
import com.train.authorityservice.service.ISystemRoleService;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.entityservice.entity.authority.Role;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * 角色模块API
 *
 * @author zhangLei
 * @serial 2019/09/24
 */
@RestController
@RequestMapping("/api/role")
@Api(tags = "角色API", hidden = true)
public class RoleApiController {

    private final ISystemRoleService systemRoleService;

    @Autowired
    public RoleApiController(ISystemRoleService systemRoleService) {
        this.systemRoleService = systemRoleService;
    }


    @GetMapping("/getRoleServiceInvocation")
    @ApiOperation(value = "根据角色id查询角色[服务调用API]")
    public Role getRoleServiceInvocation(@ApiParam(value = "角色id", required = true) @RequestParam Long roleId) {
        return systemRoleService.getRoleServiceInvocation(roleId);
    }

    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色")
    public RespRecurrence addRole(@ApiParam(value = "角色参数", required = true) @RequestBody AddRoleDTO roleDTO,
                                  BindingResult bindingResult) {
        return systemRoleService.addRole(roleDTO, bindingResult);
    }

}
