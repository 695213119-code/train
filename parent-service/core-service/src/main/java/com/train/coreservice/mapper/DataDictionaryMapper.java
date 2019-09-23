package com.train.coreservice.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.train.coreservice.vo.DataDictionariesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典Mapper类
 *
 * @author zhangLei
 * @serial 2019/09/23
 */
@Repository
public interface DataDictionaryMapper {

    List<DataDictionariesVO> queryDictionaries(Page<DataDictionariesVO> pages,
                                               @Param("key") String key,
                                               @Param("remarks") String remarks);
}
