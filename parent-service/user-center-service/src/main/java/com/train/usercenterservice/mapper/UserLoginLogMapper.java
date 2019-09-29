package com.train.usercenterservice.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.train.usercenterservice.dto.QueryUserLoginLogTabulationDTO;
import com.train.usercenterservice.vo.UserLoginLogVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户登录日志Mapper类
 *
 * @author zhangLei
 * @serial 2019/09/22
 */
@Repository
public interface UserLoginLogMapper {

    /**
     * 条件查询用户登录日志
     *
     * @param page                 分页插件
     * @param userLoginLogQueryDTO 条件
     * @return List<UserLoginLogVO>
     */
    List<UserLoginLogVO> queryUserLoginLog(Page<UserLoginLogVO> page,
                                           @Param("userLoginLogQueryDTO") QueryUserLoginLogTabulationDTO userLoginLogQueryDTO);
}
