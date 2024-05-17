package com.hito.mapper;

import com.hito.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user (ACCOUNT_ID, NAME, TOKEN, CREATE_TIME, UPDATE_TIME) values (#{accountId},#{name},#{token},#{createTime},#{updateTime})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
}
