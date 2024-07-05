package com.hito.controller;

import com.hito.pojo.Result;
import com.hito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> loginUser,HttpServletResponse response) {
        String username = loginUser.get("username");
        String hash2 = loginUser.get("hash2");
        String verificationCode = loginUser.get("verificationCode");
        String hash1_db = userService.login(username, hash2, verificationCode);
        String token = userService.getToken(username);
        if (hash1_db == "") {
            return Result.error("用户名或密码错误");
        }
        if (loginUser != null) {
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            return Result.success();
        }
        return Result.success("登录成功");
    }
}
