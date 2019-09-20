package com.train.authorityservice.authority.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.train.authorityservice.authority.mapper.JurisdictionMapper;
import com.train.authorityservice.authority.service.IJurisdictionService;
import com.train.entityservice.entity.authority.Jurisdiction;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2019-09-21
 */
@Service
public class JurisdictionServiceImpl extends ServiceImpl<JurisdictionMapper, Jurisdiction> implements IJurisdictionService {

}
