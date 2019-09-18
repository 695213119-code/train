package com.train.usercenterservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.train.commonservice.constant.CommonConstant;
import com.train.commonservice.constant.SqlConstant;
import com.train.commonservice.constant.user.UserConstant;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.commonservice.utils.RandomUtils;
import com.train.usercenterservice.utils.RedisUtils;
import com.train.usercenterservice.dto.UserLoginDTO;
import com.train.usercenterservice.dto.UserRegisterDTO;
import com.train.commonservice.entity.user.User;
import com.train.usercenterservice.service.IUserCenterService;
import com.train.usercenterservice.user.service.IUserService;
import com.train.usercenterservice.vo.UserInfoVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Autowired
    public UserCenterServiceImpl(IUserService userService, RedisUtils redisUtils) {
        this.userService = userService;
        this.redisUtils = redisUtils;
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
    public RespRecurrence<UserInfoVO> userLogin(UserLoginDTO userLoginDTO) {

        String type = userLoginDTO.getType();

        final String accountPassword = "1";
        final String phoneCode = "2";

        switch (type) {
            case accountPassword:
                return userLoginToAccountPassword(userLoginDTO.getPhone(), userLoginDTO.getPassword());
            case phoneCode:
                return userLoginToPhoneCode(userLoginDTO.getPhone(), userLoginDTO.getCode());
            default:
                return new RespRecurrence<UserInfoVO>().failure("非法的登录类型");
        }
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

    /**
     * 账号密码登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return RespRecurrence
     */
    private RespRecurrence<UserInfoVO> userLoginToAccountPassword(String phone, String password) {
        User user = userService.selectOne(new EntityWrapper<User>().eq(SqlConstant.SQL_FIELD_PHONE, phone).
                eq(CommonConstant.SQL_DELETE_SIGN, CommonConstant.SQL_DELETE_SIGN_NOT));
        if (null == user) {
            return new RespRecurrence<UserInfoVO>().failure("用户不存在");
        }
        if (!password.equals(user.getPassword())) {
            return new RespRecurrence<UserInfoVO>().failure("密码输入错误");
        }
        UserInfoVO userInfoVO = processingLoginResults(user);
        return new RespRecurrence<UserInfoVO>().success(userInfoVO);
    }


    /**
     * 手机号验证码登录
     *
     * @param phone 手机号
     * @param code  验证码
     * @return RespRecurrence
     */
    private RespRecurrence<UserInfoVO> userLoginToPhoneCode(String phone, String code) {
        return new RespRecurrence<UserInfoVO>().success();
    }


    /**
     * 清空用户残留的redis缓存
     *
     * @param phone 用户手机号
     */
    private void clearUserResidualCache(String phone) {
        String redisKey = UserConstant.USER_TOKEN_REDIS_KEY + phone;
        String tokenKey = redisUtils.get(redisKey);
        if (StringUtils.isNotEmpty(tokenKey)) {
            redisUtils.delete(tokenKey);
        }
    }

    /**
     * 处理pc端登录结果
     *
     * @param user 用户对象
     * @return UserInfoVO
     */
    private UserInfoVO processingLoginResults(User user) {
        //清空用户的残留缓存
        clearUserResidualCache(user.getPhone());
        String token = RandomUtils.generateToken();
        String userJsonString = JSONObject.toJSONString(user);
        redisUtils.set(token, userJsonString, 24, TimeUnit.HOURS);
        String tokenKey = UserConstant.USER_TOKEN_REDIS_KEY + user.getPhone();
        redisUtils.set(tokenKey, token, 24, TimeUnit.HOURS);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        userInfoVO.setUserId(String.valueOf(user.getId()));
        userInfoVO.setToken(token);
        return userInfoVO;
    }

}
