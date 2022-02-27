package com.shf.swaggerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {
//    配置多个分组
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    //    配置leswagger的docket的bean实例
    @Bean
    public Docket docket(Environment environment) {
//        设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test", "default");
//        获取项目的环境
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
//        enable 是否启用swagger
                .enable(flag)
                .select()
//        RequestHandlerSelectors 配置要扫描接口的方式
//        basePackage 配置要扫描的包
//        any()  扫描全部
//        none() 不扫描
//        withClassAnnotation  扫描类上的注解
//        withMethodAnnotation  扫描包上的注解
                .apis(RequestHandlerSelectors.basePackage("com.shf.swaggerdemo.controller"))
//        paths  过滤什么路径
//                .paths(PathSelectors.none())
                .build();
    }

    //    配置swagger作者信息
    public ApiInfo getApiInfo() {
        Contact contact = new Contact("shf", "", "");
        return new ApiInfo("我的网站",
                "励精图治",
                "v1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
