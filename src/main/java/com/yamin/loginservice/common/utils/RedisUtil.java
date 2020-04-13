package com.yamin.loginservice.common.utils;

import com.yamin.loginservice.common.exceptions.ProxyRedisException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 *redis简单操作工具类
 * TODO
 */
@Component
public class RedisUtil {


    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;

    @Value("${redis.prefix}")
    private String prefix;

    @Value("${redis.separator}")
    private String separator;

    /**
     * 永久写入redis缓存中
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) throws ProxyRedisException {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(this.prefix + this.separator + key, value);
            result = true;
        } catch (Exception e) {
            throw new ProxyRedisException("缓存:" + key + "发生异常");
        }

        return result;
    }

    /**
     * 有时间限制写入redis缓存中
     *
     * @param key
     * @param value
     * @param expireTime 过期时间
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) throws ProxyRedisException{
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(this.prefix + this.separator + key, value, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            throw new ProxyRedisException("缓存:" + key + "发生异常");
        }
        return result;
    }

    /**
     * 取出对应key的缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(this.prefix + this.separator + key);
    }

    /**
     * 判断缓存中是否存在这个key
     * @param key
     * @return
     */
    public boolean exist(final String key) {
        Boolean result = redisTemplate.hasKey(this.prefix + this.separator + key);
        return result;
    }

    /**
     * 删除对应的缓存
     * @param key
     */
    public void remove(final String key) {
        if (exist(this.prefix + this.separator + key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 删除对应的缓存
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(this.prefix + this.separator + key);
        }
    }
}
