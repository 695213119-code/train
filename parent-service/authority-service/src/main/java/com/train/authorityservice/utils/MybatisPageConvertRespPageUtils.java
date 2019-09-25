package com.train.authorityservice.utils;


import com.train.commonservice.recurrence.Page;

/**
 * mybatis的page转换为自定义的respPage
 *
 * @author zhangLei
 * @serial 2019/09/22
 */
public class MybatisPageConvertRespPageUtils {


    /**
     * 数据转换
     *
     * @param page mybatis的page
     * @return Page 自定义page
     */
    public static Page convert(com.baomidou.mybatisplus.plugins.Page page) {
        Page myPage = new Page();
        myPage.setTotal(page.getTotal());
        myPage.setTotalPages(page.getPages());
        myPage.setCurrent(page.getCurrent());
        myPage.setSize(page.getSize());
        return myPage;
    }

}
