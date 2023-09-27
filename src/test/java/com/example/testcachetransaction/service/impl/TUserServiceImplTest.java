package com.example.testcachetransaction.service.impl;

import com.example.testcachetransaction.domain.TUser;
import com.example.testcachetransaction.service.TUserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Objects;

@SpringBootTest
class TUserServiceImplTest {

    @Resource
    DataSource dataSource;

    @Resource
    TUserService tUserService;

    @Resource
    RedisTemplate<?,?> redisTemplate;

    @BeforeEach
    public void before() throws Exception {

        String createTable = """
                create table if not exists t_user( age int,name varchar(32) )
                """;
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement(createTable).execute();
            connection.prepareStatement("TRUNCATE table t_user").execute();
        }

        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().serverCommands().flushDb();
    }

    @Test
    public void test01() {

        TUser user = new TUser();
        user.setAge(15);
        user.setName("Tom");
        tUserService.save(user);

        // load to cache
        tUserService.getUser(user.getName());

        user.setAge(16);
        tUserService.updateAndGetUser(user);

    }

}