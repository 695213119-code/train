package com.train.usercenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 此服务为用户中心服务
 *
 * @author zhangLei
 * @serial 2019/09/13
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAspectJAutoProxy
@MapperScan("com.train.usercenterservice.*.mapper")
public class UserCenterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterServiceApplication.class, args);
    }

}
