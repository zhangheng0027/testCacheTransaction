package com.example.testcachetransaction;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

@SpringBootTest
class TestCacheTransactionApplicationTests {

    @Resource
    CacheTransaction cacheTransaction;

    @Resource
    RedisTemplate<?,?> redisTemplate;

    @Test
    public void test01() {
        // flush db
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().serverCommands().flushDb();
        System.out.println(cacheTransaction.cacheStr("abc"));
        System.out.println(cacheTransaction.cacheStr("abc"));
        System.out.println(cacheTransaction.cacheStr("abc"));
    }


    @Test
    public void test02() {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().serverCommands().flushDb();
        cacheTransaction.cacheTransaction("abc");
        System.out.println(cacheTransaction.cacheStr("abc"));
    }

}
