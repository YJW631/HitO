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

    @Select("select password from user where username=#{username};")
    String selectByUsername(String username);

    @Select("select token from user where username=#{username};")
    String selectTokenByUsername(String username);

    @Select("select * from user where username=#{username} and name=#{name} and email=#{email};")
    List<User> findByRecoverInfo(@Param("username") String username, @Param("name") String name, @Param("email") String email);

    @Update("update user set password=#{password} where username=#{username};")
    void updatePassword(@Param("username") String username, @Param("password") String password);

    @Update("update user set password=#{newHash1} where username=#{username};")
    void updateHash1(@Param("username") String username, @Param("newHash1") String newHash1);

    @Select("select * from user where id=#{id}")
    User selectById(Integer integer);

    @Select("select username from user where id=#{id};")
    String findUsernameById(Integer id);

    @Delete("delete from user where id=#{id};")
    void deleteById(Integer id);

    @Select("select * from user where username=#{username} order by create_time desc limit #{offset},#{pageSize};")
    List<User> findAllByUsername(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize,@Param("username") String username);

    @Select("select count(*) from user where username=#{username}; ;")
    Integer countByUsername(String username);
}
