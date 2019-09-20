package com.train.authorityservice.mapper;


import com.train.entityservice.entity.vo.UserAuthorityVO;

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


    List<UserAuthorityVO> getUserAuthority(@Param("userId") Long userId);
}
