package com.jaeden.pin.infrastructure.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 类TemplateCache.java
 *
 */
@Component
@CacheConfig(cacheNames = "templateMap")
public class TemplateCache {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setId(Integer userId, String templateId) {
        ListOperations<String, String> operations = redisTemplate.opsForList();
        operations.rightPush(String.valueOf(userId), templateId);
    }

    public String getTemplateId(Integer userId) {
        ListOperations<String, String> operations = redisTemplate.opsForList();
        return operations.leftPop(String.valueOf(userId));
    }
}
