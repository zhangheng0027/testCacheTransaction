package com.example.testcachetransaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testcachetransaction.domain.TUser;
import com.example.testcachetransaction.service.TUserService;
import com.example.testcachetransaction.mapper.TUserMapper;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author hzhang
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-09-27 15:12:35
*/
@Log4j2
@Service
@CacheConfig(cacheNames = "t_user")
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService {


    @Lazy
    @Resource
    TUserServiceImpl self;

    @Override
    @Cacheable(key = "#name")
    public TUser getUser(String name) {
        return lambdaQuery().eq(TUser::getName, name).last("limit 1").one();
    }

    @Override
    @CachePut
    public TUser updateUser(TUser user) {
        lambdaUpdate().set(TUser::getAge, user.getAge())
                .eq(TUser::getName, user.getName()).update();
        return user;
    }

    @Override
    @Transactional
    public TUser updateAndGetUser(TUser user) {
        TUser user1 = self.getUser(user.getName());
        log.info("update before user: {}", user1);

        self.updateUser(user);
        TUser user2 = self.getUser(user.getName());
        log.info("update after user: {}", user2);

        TUser one = lambdaQuery().eq(TUser::getName, user.getName()).last("limit 1").one();
        log.info("db user: {}", one);

        return user2;
    }


}




