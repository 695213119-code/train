package com.train.coreservice.service;

import com.train.commonservice.exception.BusinessException;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.coreservice.dto.FiltrateInterfaceDTO;

/**
 * 核心API接口类
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
public interface ICoreService {
    /**
     * 校验请求接口是否需要token
     *
     * @param path 接口路径
     * @return boolean
     */
    boolean checkPathNeedsToken(String path);

    /**
     * 添加过滤接口
     *
     * @param filtrateInterfaceDTO 过滤接口参数类
     * @return RespRecurrence
     */
    RespRecurrence addFiltrateInterface(FiltrateInterfaceDTO filtrateInterfaceDTO);
}
