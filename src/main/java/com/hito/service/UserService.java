package com.hito.service;

import com.hito.pojo.Result;
import com.hito.vo.User;

import java.util.List;

public interface UserService {

    /**
     * 添加用户
     */
    Result add(User user);

    /**
     * 登录
     */
    String login(String username, String hash2, String verificationCode);

    /**
     * 根据token查询用户
     */
    User findUserByToken(String token);

    /**
     * 查询所有用户
     */
    List<User> findAllUser(Integer pageNumber, Integer pageSize);

    /**
     * 查询用户总数
     */
    Integer findUserNumber();

    /**
     * 查询username对应的token
     */
    String getToken(String username);

    /**
     * 找回密码
     */
    Result recover(User user);

    /**
     * 修改密码
     */
    void change(String username, String newHash1);

    /**
     * 删除id对应的用户
     */
    void delete(Integer id);

    /**
     * 根据username查询符合条件的所有用户
     */
    List<User> findAllUserByUsername(Integer pageNumber, Integer pageSize, String username);

    /**
     * 根据username查询符合条件的用户总数
     */
    Integer findUserNumberByUsername(String username);
}
