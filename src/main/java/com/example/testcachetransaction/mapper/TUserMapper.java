package com.example.testcachetransaction.mapper;

import com.example.testcachetransaction.domain.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author hzhang
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-09-27 15:12:35
* @Entity com.example.testcachetransaction.domain.TUser
*/
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {

}




