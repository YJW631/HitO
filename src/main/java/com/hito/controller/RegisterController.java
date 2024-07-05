package com.hito.controller;

import com.hito.pojo.Result;
import com.hito.service.UserService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        user.setToken(UUID.randomUUID().toString());
        return userService.add(user);
    }

    /**
     * 找回密码
     */
    @PostMapping("/recover")
    public Result recover(@RequestBody User user) {
        return userService.recover(user);
    }

    /**
     * 修改密码
     */
    @PutMapping("/user/change")
    public Result changePassword(@RequestBody Map<String, String> changeInfo) {
        String username = changeInfo.get("username");
        String newHash1 = changeInfo.get("newHash1");
        userService.change(username, newHash1);
        return Result.success();
    }
}
