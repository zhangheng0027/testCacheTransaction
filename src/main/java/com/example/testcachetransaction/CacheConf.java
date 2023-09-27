package com.example.testcachetransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConf {

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder();
        // 启动事务
        builder.cacheWriter(RedisCacheWriter.lockingRedisCacheWriter(redisTemplate.getConnectionFactory()));
        RedisCacheManager build = builder.build();
        build.setTransactionAware(true);
        return build;
    }

}
