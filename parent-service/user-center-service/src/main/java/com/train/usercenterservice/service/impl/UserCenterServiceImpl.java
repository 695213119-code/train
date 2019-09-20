package com.train.usercenterservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.train.commonservice.constant.CommonConstant;
import com.train.commonservice.constant.SqlConstant;
import com.train.commonservice.constant.user.UserConstant;
import com.train.commonservice.enumeration.CommonEnum;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.commonservice.utils.RandomUtils;
import com.train.entityservice.entity.vo.UserAuthorityVO;
import com.train.usercenterservice.dto.UserManagementLoginDTO;
import com.train.usercenterservice.remote.authority.RemoteAuthorityService;
import com.train.usercenterservice.utils.RedisUtils;
import com.train.usercenterservice.dto.UserRegisterDTO;
import com.train.entityservice.entity.user.User;
import com.train.usercenterservice.service.IUserCenterService;
import com.train.usercenterservice.user.service.IUserService;
import com.train.usercenterservice.utils.UserInfoHolderUtils;
import com.train.usercenterservice.vo.TokenVO;
import com.train.usercenterservice.vo.UserInfoVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户中心->用户模块实现类
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
@Service
public class UserCenterServiceImpl implements IUserCenterService {

    private final IUserService userService;
    private final RedisUtils redisUtils;
    private final UserInfoHolderUtils userInfoHolderUtils;
    private final RemoteAuthorityService remoteAuthorityService;


    @Autowired
    public UserCenterServiceImpl(IUserService userService, RedisUtils redisUtils, UserInfoHolderUtils userInfoHolderUtils, RemoteAuthorityService remoteAuthorityService) {
        this.userService = userService;
        this.redisUtils = redisUtils;
        this.userInfoHolderUtils = userInfoHolderUtils;
        this.remoteAuthorityService = remoteAuthorityService;
    }

    @Override
    public RespRecurrence userRegister(UserRegisterDTO userRegisterDTO) {

        //TODO 校验手机验证码


        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);

        try {
            userService.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RespRecurrence<String>().success("用户注册成功");
    }


    @Override
    public boolean checkUserToken(String userToken) {
        boolean sign = false;
        String userJsonString = redisUtils.get(userToken);
        if (StringUtils.isNotEmpty(userJsonString)) {
            User user = (User) JSONObject.parse(userJsonString);
            User userReal = userService.selectById(user.getId());
            if (null != userReal) {
                sign = true;
            }
        }
        return sign;
    }

    @Override
    public RespRecurrence userManagementLogin(UserManagementLoginDTO userManagementLoginDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RespRecurrence().failure(CommonEnum.INVALID_PARAMETER.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        User user = userService.selectOne(new EntityWrapper<User>().eq(SqlConstant.SQL_FIELD_PHONE, userManagementLoginDTO.getPhone()).
                eq(CommonConstant.SQL_DELETE_SIGN, CommonConstant.SQL_DELETE_SIGN_NOT));

        if (null == user) {
            return new RespRecurrence<UserInfoVO>().failure("用户不存在");
        }

        //TODO 密码加密待完成,目前先不处理
        if (!user.getPassword().equals(userManagementLoginDTO.getPassword())) {
            return new RespRecurrence<UserInfoVO>().failure("密码输入错误");
        }

        clearUserResidualCache(user.getPhone());
        String token = RandomUtils.generateToken();
        redisUtils.set(token, JSONObject.toJSONString(user), 24, TimeUnit.HOURS);
        redisUtils.set(UserConstant.USER_TOKEN_REDIS_KEY + user.getPhone(), token, 24, TimeUnit.HOURS);
        return new RespRecurrence<>().success(new TokenVO(token));
    }

    @Override
    public RespRecurrence getUserDetails(String key) {
        Long userId = userInfoHolderUtils.getUserId();
        User user = userService.selectOne(new EntityWrapper<User>().eq(SqlConstant.SQL_FIELD_ID, userId).
                eq(CommonConstant.SQL_DELETE_SIGN, CommonConstant.SQL_DELETE_SIGN_NOT));
        //TODO 删除用户,必须同时删除redis的token信息
        if (null == user) {
            return new RespRecurrence().failure("不存在的用户");
        }

        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(String.valueOf(user.getId()));

        //TODO 查询角色

        //查询权限
        List<UserAuthorityVO> userAuthority = remoteAuthorityService.getUserAuthority(user.getId());
        userInfoVO.setUserAuthority(userAuthority);

        final String phone = "1";
        final String wechat = "2";
        final String qq = "3";
        if (phone.equals(key)) {
            BeanUtils.copyProperties(user, userInfoVO);
        } else if (wechat.equals(key)) {

        } else if (qq.equals(key)) {

        } else {
            return new RespRecurrence().failure("非法的查询类型");
        }
        return new RespRecurrence<>().success(userInfoVO);
    }


    /**
     * 清空用户残留的token-redis缓存
     *
     * @param phone 用户手机号
     */
    private void clearUserResidualCache(String phone) {
        String userTokenRedisKey = redisUtils.get(UserConstant.USER_TOKEN_REDIS_KEY + phone);
        if (StringUtils.isNotEmpty(userTokenRedisKey)) {
            redisUtils.delete(userTokenRedisKey);
        }
    }


}
