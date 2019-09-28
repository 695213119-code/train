package com.train.authorityservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.train.authorityservice.authority.service.IJurisdictionService;
import com.train.authorityservice.dto.AuthorityAddDTO;
import com.train.authorityservice.mapper.AuthorityMapper;
import com.train.authorityservice.service.IAuthorityService;
import com.train.commonservice.constant.CommonConstant;
import com.train.commonservice.constant.SqlConstant;
import com.train.commonservice.enumeration.CommonEnum;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.entityservice.entity.authority.Jurisdiction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限模块实现类
 *
 * @author zhangLei
 * @serial 2019/09/21
 */

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceImpl.class);


    private final AuthorityMapper authorityMapper;
    private final IJurisdictionService jurisdictionService;


    @Autowired
    public AuthorityServiceImpl(AuthorityMapper authorityMapper, IJurisdictionService jurisdictionService) {
        this.authorityMapper = authorityMapper;
        this.jurisdictionService = jurisdictionService;
    }

    @Override
    public List<Jurisdiction> getUserAuthorityServiceInvocation(Long userId) {
        return authorityMapper.getUserAuthority(userId);
    }

    @Override
    public RespRecurrence addAuthority(AuthorityAddDTO authorityAddDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RespRecurrence().failure(CommonEnum.INVALID_PARAMETER.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Jurisdiction jurisdictionCheck = jurisdictionService.selectOne(new EntityWrapper<Jurisdiction>().eq(SqlConstant.SQL_FIELD_IDENTIFICATION, authorityAddDTO.getIdentification())
                .eq(CommonConstant.SQL_DELETE_SIGN, CommonConstant.SQL_DELETE_SIGN_NOT));

        if (null != jurisdictionCheck) {
            return new RespRecurrence().failure(CommonEnum.BUSINESS_CODE.getCode(), "权限标识重复啦");
        }

        Jurisdiction jurisdiction = new Jurisdiction();
        BeanUtils.copyProperties(authorityAddDTO, jurisdiction);

        try {
            jurisdictionService.insert(jurisdiction);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加权限失败,,参数:{}", authorityAddDTO);
        }

        return new RespRecurrence().success();
    }


}
