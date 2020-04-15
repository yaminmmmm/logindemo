package com.yamin.loginservice.common.config;

import com.yamin.loginservice.common.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //todo 添加不需要拦截的路径
        List<String> excludePaths = new ArrayList<>();
        excludePaths.add("/common/login");
        excludePaths.add("/common/register");


        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                //不需要拦截静态资源文件
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**")
                .excludePathPatterns(excludePaths);
    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
}
