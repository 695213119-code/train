package com.train.usercenterservice.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.train.entityservice.entity.user.LoginLog;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 登录日志表 Mapper 接口
 * </p>
 *
 * @author Administrator
 * @since 2019-09-18
 */
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog> {

}
