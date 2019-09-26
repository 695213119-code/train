package com.train.usercenterservice.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.train.usercenterservice.dto.QueryUserTabulationDTO;
import com.train.usercenterservice.vo.UserTabulationVO;
import com.train.usercenterservice.vo.UserThirdVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户中心mapper
 *
 * @author zhangLei
 * @serial 2019/9/26
 */
@Repository
public interface UserCenterMapper {

    /**
     * 根据条件查询用户列表
     *
     * @param page                   分页插件
     * @param queryUserTabulationDTO 查询条件
     * @return List<UserTabulationVO>
     */
    List<UserTabulationVO> queryUserTabulation(Page<UserTabulationVO> page,
                                               @Param("queryUserTabulationDTO") QueryUserTabulationDTO queryUserTabulationDTO);


    /**
     * 根据用户id查询用户第三方数据
     *
     * @param id 用户id
     * @return List<UserThirdVO>
     */
    List<UserThirdVO> findUserThirdVOByUserId(@Param("id") Long id);


}



