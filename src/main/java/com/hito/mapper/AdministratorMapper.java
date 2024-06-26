package com.hito.mapper;

import com.hito.vo.Administrator;
import com.hito.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdministratorMapper {
    @Select("select * from administrator where username=#{username} and password =#{password};")
    Administrator findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from administrator where administrator_token=#{token};")
    Administrator findByToken(String token);

    @Select("select password from administrator where username=#{username};")
    String selectByUsername(String username);

    @Select("select administrator_token from administrator where username=#{username};")
    String selectTokenByUsername(String username);
}
