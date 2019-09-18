package com.train.coreservice.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.train.commonservice.entity.core.Dictionary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
