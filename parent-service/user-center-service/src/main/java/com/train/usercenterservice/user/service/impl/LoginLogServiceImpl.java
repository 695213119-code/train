package com.train.usercenterservice.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.train.commonservice.entity.user.LoginLog;
import com.train.usercenterservice.user.mapper.LoginLogMapper;
import com.train.usercenterservice.user.service.ILoginLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2019-09-18
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {


}
