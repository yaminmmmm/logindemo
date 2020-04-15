package com.yamin.loginservice.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2).groupName("login-service")
                .apiInfo(apiInfo()).select()
                .paths(PathSelectors.any())
                .apis(withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("login doc")
                .contact(new Contact("yamin","","ym534766545@outlook.com"))
                .version("1.0")
                .description("登录模块API文档")
                .build();
    }
}
