package com.hito.controller;

import com.hito.pojo.Result;
import com.hito.service.UserService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user, HttpServletResponse response){
        user.setToken(UUID.randomUUID().toString());
        return userService.add(user);
    }

    @PostMapping("/recover")
    public Result recover(@RequestBody User user){
        return userService.recover(user);
    }

    @PutMapping("/user/change")
    public Result changePassword(@RequestBody Map<String, String> changeInfo){
        String username=changeInfo.get("username");
        String newHash1=changeInfo.get("newHash1");
        userService.change(username,newHash1);
        return Result.success();
    }

}
