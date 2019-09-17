package com.train.coreservice.core.service.impl;

import com.train.commonservice.entity.core.Interface;
import com.train.coreservice.core.mapper.InterfaceMapper;
import com.train.coreservice.core.service.IInterfaceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口登记表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2019-09-17
 */
@Service
public class InterfaceServiceImpl extends ServiceImpl<InterfaceMapper, Interface> implements IInterfaceService {

}
