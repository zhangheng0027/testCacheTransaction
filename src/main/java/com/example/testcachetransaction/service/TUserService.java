package com.example.testcachetransaction.service;

import com.example.testcachetransaction.domain.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hzhang
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-09-27 15:12:35
*/
public interface TUserService extends IService<TUser> {

    public TUser getUser(String name);

    public TUser updateUser(TUser user);

    public TUser updateAndGetUser(TUser user);

}
