package com.yamin.loginservice.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.yamin.loginservice.orm.mapper")
public class MybatisConfig {
}
