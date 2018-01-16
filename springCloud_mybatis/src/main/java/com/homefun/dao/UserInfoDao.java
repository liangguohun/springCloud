package com.homefun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.homefun.beans.UserInfo;


@Mapper
public interface UserInfoDao {
    @Select("SELECT id, username FROM userinfo WHERE username = #{username}")
    UserInfo findByUsername(@Param(value = "username") String username);
    
    int save(UserInfo user);
    int updatePart(UserInfo user);
    UserInfo selectById(Integer id);
    int updateById(UserInfo user);
    int deleteById(Integer id);
    List<UserInfo> queryAll();
    void insertBatch(List<UserInfo> users);
    void batchUpdate(List<UserInfo> users);
}
