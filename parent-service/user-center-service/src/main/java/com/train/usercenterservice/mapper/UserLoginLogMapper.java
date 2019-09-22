package com.train.usercenterservice.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.train.usercenterservice.dto.UserLoginLogQueryDTO;
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


    List<UserLoginLogVO> queryUserLoginLog(Page<UserLoginLogVO> page, @Param("userLoginLogQueryDTO") UserLoginLogQueryDTO userLoginLogQueryDTO);
}
