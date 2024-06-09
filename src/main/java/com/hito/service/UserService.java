package com.hito.service;

import com.hito.pojo.Result;
import com.hito.vo.User;

import java.util.List;

public interface UserService {


    Result add(User user);

    User login(String username, String password);

    User findUserByToken(String token);

    List<User> findAllUser(Integer pageNumber, Integer pageSize);

    Integer findUserNumber();
}
