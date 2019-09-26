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

    /**
     * 根据条件分页查询字典列表
     *
     * @param pages   分页插件
     * @param key     字典key
     * @param remarks 字典备注
     * @return List<DataDictionariesVO>
     */
    List<DataDictionariesVO> queryDictionaries(Page<DataDictionariesVO> pages,
                                               @Param("key") String key,
                                               @Param("remarks") String remarks);
}
