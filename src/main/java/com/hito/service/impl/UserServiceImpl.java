package com.hito.service.impl;

import com.hito.mapper.UserMapper;
import com.hito.pojo.Result;
import com.hito.service.UserService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public String login(String username, String hash2, String verificationCode) {
        String hash1_db=userMapper.selectByUsername(username);
        if(hash1_db==""){
            return "";
        }
        String hash2_nd="";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest((hash1_db+verificationCode).getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                sb.append(String.format("%02x", b));
            }
            hash2_nd=sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(hash2_nd.equals(hash2)){
            return hash1_db;
        }
        return "";
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

    @Override
    public String getToken(String username) {
        return userMapper.selectTokenByUsername(username);
    }
}
