package com.hito.mapper;

import com.hito.vo.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministratorMapper {
    /**
     * 根据用户名和密码查询管理员对象
     */
    @Select("select * from administrator where username=#{username} and password =#{password};")
    Administrator findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据token查询管理员对象
     */
    @Select("select * from administrator where administrator_token=#{token};")
    Administrator findByToken(String token);

    /**
     * 根据用户名查询密码
     */
    @Select("select password from administrator where username=#{username};")
    String selectByUsername(String username);

    /**
     * 根据用户名查询token
     */
    @Select("select administrator_token from administrator where username=#{username};")
    String selectTokenByUsername(String username);
}
