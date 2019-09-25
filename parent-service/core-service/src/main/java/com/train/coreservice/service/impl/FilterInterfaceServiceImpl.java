package com.train.coreservice.service.impl;

import com.train.commonservice.recurrence.RespRecurrence;
import com.train.coreservice.core.service.IInterfaceService;
import com.train.coreservice.dto.FiltrateInterfaceDTO;
import com.train.coreservice.service.IFilterInterfaceService;
import com.train.entityservice.entity.core.Interface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 过滤接口实现类
 *
 * @author zhangLei
 * @serial 2019/9/25
 */
@Service
public class FilterInterfaceServiceImpl implements IFilterInterfaceService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FilterInterfaceServiceImpl.class);

    private final IInterfaceService iInterfaceService;

    @Autowired
    public FilterInterfaceServiceImpl(IInterfaceService iInterfaceService) {
        this.iInterfaceService = iInterfaceService;
    }

    @Override
    public RespRecurrence addFiltrateInterface(FiltrateInterfaceDTO filtrateInterfaceDTO) {
        Interface inter = new Interface();
        BeanUtils.copyProperties(filtrateInterfaceDTO, inter);
        try {
            iInterfaceService.insert(inter);
        } catch (Exception e) {
            LOGGER.error("添加过滤接口失败 原因:{} 参数:{}", e.getMessage(), filtrateInterfaceDTO);
            return new RespRecurrence<>().failure();
        }
        return new RespRecurrence<>().success();
    }
}
