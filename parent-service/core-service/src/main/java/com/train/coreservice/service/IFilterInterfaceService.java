package com.train.coreservice.service;

import com.train.commonservice.recurrence.RespRecurrence;
import com.train.coreservice.dto.FiltrateInterfaceDTO;

/**
 * 过滤接口类
 *
 * @author zhangLei
 * @serial 2019/9/25
 */
public interface IFilterInterfaceService {

    /**
     * 添加过滤接口
     *
     * @param filtrateInterfaceDTO 过滤接口参数类
     * @return RespRecurrence
     */
    RespRecurrence addFiltrateInterface(FiltrateInterfaceDTO filtrateInterfaceDTO);
}
