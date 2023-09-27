package com.example.testcachetransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
public class TestCacheTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCacheTransactionApplication.class, args);
    }

}
