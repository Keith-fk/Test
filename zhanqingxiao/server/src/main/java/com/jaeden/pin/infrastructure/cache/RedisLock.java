package com.jaeden.pin.infrastructure.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private String prefix = "publish-";

    public boolean getLock(Integer userId){
        String key = prefix.concat(String.valueOf(userId));
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        return operations.setIfAbsent(key,"lock",3L, TimeUnit.SECONDS);
    }

}
