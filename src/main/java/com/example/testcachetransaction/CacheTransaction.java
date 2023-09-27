package com.example.testcachetransaction;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@CacheConfig(cacheNames = "cacheTransaction")
@Component
public class CacheTransaction {

    private static final AtomicInteger ai = new AtomicInteger(0);


    @Lazy
    @Resource
    CacheTransaction self;


    @Cacheable(key = "#s")
    public String cacheStr(String s) {
        return s + ai.incrementAndGet();
    }

    @Transactional
    public String cacheTransaction(String s) {
        String s1 = self.cacheStr(s);
        log.info("first cache: {}", s1);
        String s2 = self.cacheStr(s);
        log.info("second cache: {}", s2);

        return s2;
    }


}
