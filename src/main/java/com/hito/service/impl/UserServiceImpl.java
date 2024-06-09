package com.hito.service.impl;

import com.hito.mapper.UserMapper;
import com.hito.pojo.Result;
import com.hito.service.UserService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result add(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        try {
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            return Result.error("用户名重复");
        }
        return Result.success();
    }

    @Override
    public User login(String username, String password) {
        return userMapper.findByUsernameAndPassword(username,password);
    }

    @Override
    public User findUserByToken(String token) {
        return userMapper.findByToken(token);
    }

    @Override
    public List<User> findAllUser(Integer pageNumber, Integer pageSize) {
        return(userMapper.findAll((pageNumber - 1) * pageSize, pageSize));
    }

    @Override
    public Integer findUserNumber() {
        return userMapper.count();
    }
}
