package com.hito.service;

import com.hito.pojo.Result;
import com.hito.vo.User;

import java.util.List;

public interface UserService {


    Result add(User user);

    String login(String username, String hash2, String verificationCode);

    User findUserByToken(String token);

    List<User> findAllUser(Integer pageNumber, Integer pageSize);

    Integer findUserNumber();

    String getToken(String username);

    Result recover(User user);

    void change(String username, String newHash1);
    void delete(Integer id);

    List<User> findAllUserByUsername(Integer pageNumber, Integer pageSize, String username);

    Integer findUserNumberByUsername(String username);
}
