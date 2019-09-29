package com.train.coreservice.service;

import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.coreservice.dto.AddDictionariesDTO;
import com.train.coreservice.dto.EditDictionariesDTO;
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
     * @param dictionariesDTO 字典参数类
     * @param bindingResult      BindingResult
     * @return RespRecurrence
     */
    RespRecurrence addDictionaries(AddDictionariesDTO dictionariesDTO, BindingResult bindingResult);

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
     * @param dictionariesDTO 字典参数类
     * @param bindingResult       BindingResult
     * @return RespRecurrence
     */
    RespRecurrence editDictionaries(EditDictionariesDTO dictionariesDTO, BindingResult bindingResult);
}
