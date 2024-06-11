package com.hito.controller;

import com.hito.dto.QuestionDto;
import com.hito.pojo.Result;
import com.hito.service.QuestionService;
import com.hito.service.UserService;
import com.hito.vo.Question;
import com.hito.vo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    @Value("${website.head}")
    private String websiteHead;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/user/get/name")
    public Result getNameBySession(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return Result.success(user.getName());
    }

    @GetMapping("/userlogout")
    public Result logOut(HttpServletResponse response) {
        response.addCookie(new Cookie("token", null));
        return Result.success();
    }

    @GetMapping("/user/questionlist")
    public Result getQuestionList(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "tag", defaultValue = "0") Integer tag) {
        if (tag == 0) {
            List<QuestionDto> questionDtoList = questionService.findAllQuestion(pageNumber, pageSize);
            return Result.success(questionDtoList);
        } else {
            List<QuestionDto> questionDtoList = questionService.findQuestionByTag(pageNumber, pageSize, tag);
            return Result.success(questionDtoList);
        }
    }

    @GetMapping("/user/questionnumber")
    public Result getQuestionNumber(@RequestParam(name = "tag", defaultValue = "0") Integer tag) {
        if (tag == 0) {
            Integer questionNumber = questionService.count();
            return Result.success(questionNumber);
        } else {
            Integer questionNumber = questionService.countByTag(tag);
            return Result.success(questionNumber);
        }
    }

    @PutMapping("/user/view")
    public Result changeView(@RequestBody Map<String, String> changeInfo) {
        Integer id = Integer.valueOf(changeInfo.get("id"));
        questionService.view(id);
        return Result.success();
    }

    @GetMapping("/user/hotquestionlist")
    public Result getHotQuestionList() {
        List<QuestionDto> hotQuestionDtoList = questionService.findHotQuestion();
        return Result.success(hotQuestionDtoList);
    }

    @GetMapping("/user/searchQuestionlist")
    public Result getSearchQuestionList(@RequestParam(name = "searchText") String searchText) {
        List<QuestionDto> searchQuestionDtoList = questionService.findSearchQuestion(searchText);
        return Result.success(searchQuestionDtoList);
    }

    @GetMapping("/user/get/userinfo")
    public Result getUserBySession(HttpServletRequest request, @RequestParam("from") Integer from) {
        if (from == 0) {
            User user = (User) request.getSession().getAttribute("user");
            return Result.success(user);
        } else {
            User user = (User) request.getSession().getAttribute("userInfo");
            return Result.success(user);
        }
    }

    @PutMapping("/user/setuserinfo")
    public Result setUserInfoInSession(@RequestBody User user, HttpServletRequest request) {
        request.getSession().setAttribute("userInfo", user);
        return Result.success();
    }

}
