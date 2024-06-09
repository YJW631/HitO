package com.hito.controller;

import com.hito.dto.QuestionDto;
import com.hito.pojo.Result;
import com.hito.service.AdministratorService;
import com.hito.service.QuestionService;
import com.hito.service.UserService;
import com.hito.vo.Administrator;
import com.hito.vo.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/administratorlogin")
    public Result login(@RequestBody Administrator administrator, HttpServletRequest request, HttpServletResponse response) {
        Administrator loginAdministrator = administratorService.login(administrator.getUsername(), administrator.getPassword());
        if (loginAdministrator != null) {
            Cookie cookie = new Cookie("administrator_token", loginAdministrator.getAdministratorToken());
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            return Result.success();
        }
        return Result.error("用户名或密码错误");
    }

    @GetMapping("/administrator/userlist")
    public Result getUserList(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<User> userList = userService.findAllUser(pageNumber, pageSize);
        return Result.success(userList);
    }

    @GetMapping("/administrator/usernumber")
    public Result getUserNumber(){
        return Result.success(userService.findUserNumber());
    }

    @GetMapping("/administrator/get/name")
    public Result getNameBySession(HttpServletRequest request) {
        Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
        return Result.success(administrator.getUsername());
    }

    @GetMapping("/administratorlogout")
    public Result logOut(HttpServletResponse response) {
        response.addCookie(new Cookie("administrator_token", null));
        return Result.success();
    }

    @GetMapping("/administrator/questionnumber")
    public Result getQuestionNumber(){
        return Result.success(questionService.count());
    }

    @GetMapping("/administrator/questionlist")
    public Result getQuestionList(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<QuestionDto> questionDtoList = questionService.findAllQuestionOfAnyStatus(pageNumber, pageSize);
        return Result.success(questionDtoList);
    }

    @DeleteMapping("/administrator/pass")
    public Result pass(@RequestParam(name = "id") Integer id){
        questionService.pass(id);
        return Result.success();
    }

    @DeleteMapping("/administrator/unpass")
    public Result unPass(@RequestParam(name = "id") Integer id){
        questionService.unPass(id);
        return Result.success();
    }
}
