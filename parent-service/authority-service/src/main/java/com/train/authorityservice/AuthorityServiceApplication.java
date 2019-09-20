package com.train.authorityservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 此服务为核心权限服务
 * @author zhangLei
 * @serial 2019/09/21
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = {"com.train.authorityservice.mapper","com.train.authorityservice.authority.mapper"})
public class AuthorityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityServiceApplication.class, args);
    }

}
