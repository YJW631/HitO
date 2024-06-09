package com.hito.mapper;

import com.hito.vo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{creator};")
    User findByUsername(String creator);

    @Insert("insert into user (username, password, name, email, self_introduction, token, avatar_url, update_time, create_time) values (#{username},#{password},#{name},#{email},#{selfIntroduction},#{token},#{avatarUrl},#{createTime},#{updateTime});")
    void insert(User user);

    @Select("select * from user where username=#{username} and password =#{password};")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where token=#{token};")
    User findByToken(@Param("token") String token);

    @Select("select * from user order by create_time desc limit #{offset},#{pageSize};")
    List<User> findAll(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from user;")
    Integer count();
}
