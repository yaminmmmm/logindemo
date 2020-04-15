package com.yamin.loginservice.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

@Configuration
public class RedisConfig {


    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 配置redisTemplate序列化的设置
     *
     * @return
     */
    @Bean
    public RedisTemplate<Serializable, Object> stringSerializableRedisTemplate() {

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();

        RedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jsonSerializer);


        return redisTemplate;
    }
}
