package com.train.coreservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 此服务为核心基础服务
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.train.coreservice.*.mapper")
public class CoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreServiceApplication.class, args);
    }

}
