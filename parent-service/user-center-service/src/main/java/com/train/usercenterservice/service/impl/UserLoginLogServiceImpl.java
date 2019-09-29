package com.train.usercenterservice.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.train.commonservice.enumeration.CommonEnum;
import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.usercenterservice.dto.QueryUserLoginLogTabulationDTO;
import com.train.usercenterservice.mapper.UserLoginLogMapper;
import com.train.usercenterservice.service.IUserLoginLogService;
import com.train.usercenterservice.utils.MybatisPageConvertRespPageUtils;
import com.train.usercenterservice.vo.UserLoginLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * 用户登录日志实现类
 *
 * @author zhangLei
 * @serial 2019/09/12
 */
@Service
public class UserLoginLogServiceImpl implements IUserLoginLogService {

    private final UserLoginLogMapper userLoginLogMapper;

    @Autowired
    public UserLoginLogServiceImpl(UserLoginLogMapper userLoginLogMapper) {
        this.userLoginLogMapper = userLoginLogMapper;
    }


    @Override
    public RespPageRecurrence queryUserLoginLog(QueryUserLoginLogTabulationDTO userLoginLogTabulationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new RespPageRecurrence().failure(CommonEnum.INVALID_PARAMETER.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Page<UserLoginLogVO> page = new Page<>(userLoginLogTabulationDTO.getPage(), userLoginLogTabulationDTO.getLimit());
        List<UserLoginLogVO> list = userLoginLogMapper.queryUserLoginLog(page, userLoginLogTabulationDTO);
        return new RespPageRecurrence<>().success(list, MybatisPageConvertRespPageUtils.convert(page));
    }
}
