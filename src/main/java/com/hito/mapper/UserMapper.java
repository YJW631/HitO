package com.hito.mapper;

import com.hito.vo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据username查询用户
     */
    @Select("select * from user where username=#{creator};")
    User findByUsername(String creator);

    /**
     * 增加一条用户记录
     */
    @Insert("insert into user (username, password, name, email, self_introduction, token, avatar_url, update_time, create_time) values (#{username},#{password},#{name},#{email},#{selfIntroduction},#{token},#{avatarUrl},#{createTime},#{updateTime});")
    void insert(User user);

    /**
     * 根据username和password查询用户
     */
    @Select("select * from user where username=#{username} and password =#{password};")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据token查询用户
     */
    @Select("select * from user where token=#{token};")
    User findByToken(@Param("token") String token);

    /**
     * 查询所用用户
     */
    @Select("select * from user order by create_time desc limit #{offset},#{pageSize};")
    List<User> findAll(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    /**
     * 查询用户总数
     */
    @Select("select count(*) from user;")
    Integer count();

    /**
     * 根据username查询用户
     */
    @Select("select password from user where username=#{username};")
    String selectByUsername(String username);

    /**
     * 根据username查询token
     */
    @Select("select token from user where username=#{username};")
    String selectTokenByUsername(String username);

    /**
     * 根据修改密码信息查询用户
     */
    @Select("select * from user where username=#{username} and name=#{name} and email=#{email};")
    List<User> findByRecoverInfo(@Param("username") String username, @Param("name") String name, @Param("email") String email);

    /**
     * 更新密码
     */
    @Update("update user set password=#{password} where username=#{username};")
    void updatePassword(@Param("username") String username, @Param("password") String password);

    /**
     * 更新hash1字段（password字段）
     */
    @Update("update user set password=#{newHash1} where username=#{username};")
    void updateHash1(@Param("username") String username, @Param("newHash1") String newHash1);

    /**
     * 根据主键id查询用户
     */
    @Select("select * from user where id=#{id}")
    User selectById(Integer integer);

    /**
     * 根据主键id查询对应用户的用户名
     */
    @Select("select username from user where id=#{id};")
    String findUsernameById(Integer id);

    /**
     * 根据id删除对应用户
     */
    @Delete("delete from user where id=#{id};")
    void deleteById(Integer id);

    /**
     * 根据查询条件查询所有用户名相匹配的用户
     */
    @Select("select * from user where username=#{username} order by create_time desc limit #{offset},#{pageSize};")
    List<User> findAllByUsername(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("username") String username);

    /**
     * 根据查询条件查询用户名相匹配的用户总数
     */
    @Select("select count(*) from user where username=#{username};")
    Integer countByUsername(String username);
}
