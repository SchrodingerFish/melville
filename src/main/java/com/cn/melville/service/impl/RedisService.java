package com.cn.melville.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author qiuci
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * Set a value in Redis
     *
     * @param key   the key
     * @param value the value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * Get a value from Redis
     *
     * @param key the key
     * @return the value
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
