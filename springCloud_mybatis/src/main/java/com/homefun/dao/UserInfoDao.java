package com.homefun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.homefun.beans.UserInfo;


@Mapper
public interface UserInfoDao {
    @Select("SELECT id, username FROM userinfo WHERE username = #{username}")
    UserInfo findByUsername(@Param(value = "username") String username);
}
