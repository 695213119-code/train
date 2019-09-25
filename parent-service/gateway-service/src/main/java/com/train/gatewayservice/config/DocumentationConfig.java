package com.train.gatewayservice.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger文档资源配置类
 *
 * @author zhangLei
 * @serial 2019/9/25
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("用户中心服务Swagger", "/apigateway/user-center/v2/api-docs", "1.0.0"));
        resources.add(swaggerResource("系统核心基础服务Swagger", "/apigateway/core-service/v2/api-docs", "1.0.0"));
        resources.add(swaggerResource("权限服务Swagger", "/apigateway/authority-service/v2/api-docs", "1.0.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }


}
