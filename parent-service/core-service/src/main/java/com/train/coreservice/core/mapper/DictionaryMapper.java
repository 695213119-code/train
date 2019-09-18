package com.train.coreservice.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.train.entityservice.entity.core.Dictionary;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author Administrator
 * @since 2019-09-18
 */
@Repository
public interface DictionaryMapper extends BaseMapper<Dictionary> {
}
