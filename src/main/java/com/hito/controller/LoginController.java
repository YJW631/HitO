package com.hito.controller;

import com.hito.pojo.Result;
import com.hito.service.UserService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            Cookie cookie = new Cookie("token", loginUser.getToken());
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            return Result.success();
        }
        return Result.error("用户名或密码错误");
    }


}
