package com.train.authorityservice.authority.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.train.authorityservice.authority.mapper.RoleMapper;
import com.train.authorityservice.authority.service.IRoleService;
import com.train.entityservice.entity.authority.Role;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2019-09-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
