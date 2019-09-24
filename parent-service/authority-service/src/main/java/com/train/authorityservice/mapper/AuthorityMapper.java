package com.train.authorityservice.mapper;


import com.train.entityservice.entity.authority.Jurisdiction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限Mapper
 *
 * @author zhangLei
 * @serial 2019/09/21
 */
@Repository
public interface AuthorityMapper {

    /**
     * 根据用户id查询用户权限
     *
     * @param userId 用户id
     * @return List
     */
    List<Jurisdiction> getUserAuthority(@Param("userId") Long userId);
}
