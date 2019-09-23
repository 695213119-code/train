package com.train.coreservice.service;

import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.coreservice.dto.DictionariesAddDTO;
import com.train.coreservice.dto.DictionariesEditDTO;
import org.springframework.validation.BindingResult;

/**
 * 数据字典接口类
 *
 * @author zhangLei
 * @serial 2019/09/23
 */
public interface IDataDictionaryService {

    /**
     * 添加字典
     *
     * @param dictionariesAddDTO 字典参数类
     * @param bindingResult      BindingResult
     * @return RespRecurrence
     */
    RespRecurrence addDictionaries(DictionariesAddDTO dictionariesAddDTO, BindingResult bindingResult);

    /**
     * 获取字典列表
     *
     * @param page    page
     * @param limit   limit
     * @param key     字典的key
     * @param remarks 字典的备注
     * @return RespPageRecurrence
     */
    RespPageRecurrence queryDictionaries(Integer page, Integer limit, String key, String remarks);

    /**
     * 操作字典
     *
     * @param dictionariesEditDTO 字典参数类
     * @param bindingResult       BindingResult
     * @return RespRecurrence
     */
    RespRecurrence editDictionaries(DictionariesEditDTO dictionariesEditDTO, BindingResult bindingResult);
}
