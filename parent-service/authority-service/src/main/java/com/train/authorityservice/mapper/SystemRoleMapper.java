package com.train.authorityservice.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.train.authorityservice.vo.RoleTabulationVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色mapper
 *
 * @author zhangLei
 * @serial 2019/9/25
 */
@Repository
public interface SystemRoleMapper {

    /**
     * 条件查询角色列表
     *
     * @param pages    分页
     * @param roleName 角色名称
     * @return List<RoleTabulationVO>
     */
    List<RoleTabulationVO> queryRoleTabulation(Page<RoleTabulationVO> pages,
                                               @Param("roleName") String roleName);

}
