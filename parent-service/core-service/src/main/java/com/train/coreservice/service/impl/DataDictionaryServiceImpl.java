package com.train.coreservice.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.train.commonservice.constant.SqlConstant;
import com.train.commonservice.enumeration.CommonEnum;
import com.train.commonservice.recurrence.RespPageRecurrence;
import com.train.commonservice.recurrence.RespRecurrence;
import com.train.coreservice.core.service.IDictionaryService;
import com.train.coreservice.dto.AddDictionariesDTO;
import com.train.coreservice.dto.EditDictionariesDTO;
import com.train.coreservice.mapper.DataDictionaryMapper;
import com.train.coreservice.service.IDataDictionaryService;
import com.train.coreservice.utils.MybatisPageConvertRespPageUtils;
import com.train.coreservice.vo.DataDictionariesVO;
import com.train.entityservice.entity.core.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;

/**
 * 数据字典实现类
 *
 * @author zhangLei
 * @serial 2019/09/23
 */
@Service
public class DataDictionaryServiceImpl implements IDataDictionaryService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataDictionaryServiceImpl.class);

    private final IDictionaryService dictionaryService;
    private final DataDictionaryMapper dataDictionaryMapper;

    @Autowired
    public DataDictionaryServiceImpl(IDictionaryService dictionaryService, DataDictionaryMapper dataDictionaryMapper) {
        this.dictionaryService = dictionaryService;
        this.dataDictionaryMapper = dataDictionaryMapper;
    }

    @Override
    public RespRecurrence addDictionaries(AddDictionariesDTO dictionariesDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RespRecurrence().failure(CommonEnum.INVALID_PARAMETER.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Dictionary check = dictionaryService.selectOne(new EntityWrapper<Dictionary>().eq(SqlConstant.SQL_FIELD_KEY, dictionariesDTO.getDicKey()));
        if (null != check) {
            return new RespRecurrence<>().failure(CommonEnum.BUSINESS_CODE.getCode(), "数据字典的key重复啦");
        }

        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(dictionariesDTO, dictionary);
        try {
            dictionaryService.insert(dictionary);
        } catch (Exception e) {
            LOGGER.error("添加字典失败 原因:{} 参数:{}", e.getMessage(), dictionariesDTO);
            return new RespRecurrence<>().failure();
        }

        return new RespRecurrence<>().success();
    }

    @Override
    public RespPageRecurrence queryDictionaries(Integer page, Integer limit, String key, String remarks) {
        if (page < 1 || limit < 1) {
            return new RespPageRecurrence().failure(CommonEnum.INVALID_PARAMETER.getCode(), "page/limit不能小于1");
        }
        Page<DataDictionariesVO> pages = new Page<>(page, limit);
        List<DataDictionariesVO> dataDictionaries = dataDictionaryMapper.queryDictionaries(pages, key, remarks);
        return new RespPageRecurrence<>().success(dataDictionaries, MybatisPageConvertRespPageUtils.convert(pages));
    }

    @Override
    public RespRecurrence editDictionaries(EditDictionariesDTO dictionariesDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RespRecurrence().failure(CommonEnum.INVALID_PARAMETER.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Dictionary dictionary = new Dictionary();
        dictionary.setUpdateTime(new Date());
        BeanUtils.copyProperties(dictionariesDTO, dictionary);

        try {
            dictionaryService.updateById(dictionary);
        } catch (Exception e) {
            LOGGER.error("操作字典失败 原因:{} 参数:{}", e.getMessage(), dictionariesDTO);
            return new RespRecurrence<>().failure();
        }

        return new RespRecurrence<>().success();
    }
}
