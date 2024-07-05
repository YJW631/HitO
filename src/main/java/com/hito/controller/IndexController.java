package com.hito.controller;

import com.hito.dto.QuestionDto;
import com.hito.pojo.Result;
import com.hito.service.QuestionService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    private QuestionService questionService;

    /**
     * 根据session获取当前登录用户的名字
     */
    @GetMapping("/user/get/name")
    public Result getNameBySession(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return Result.success(user.getName());
    }

    /**
     * 登出
     */
    @GetMapping("/userlogout")
    public Result logOut(HttpServletResponse response) {
        response.addCookie(new Cookie("token", null));
        return Result.success();
    }

    /**
     * 获取问题列表
     */
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

    /**
     * 获取问题总数
     */
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

    /**
     * 修改当前cookie中记录的问题号
     */
    @PutMapping("/user/view")
    public Result changeView(@RequestBody Map<String, String> changeInfo) {
        Integer id = Integer.valueOf(changeInfo.get("id"));
        questionService.view(id);
        return Result.success();
    }

    /**
     * 获取当前热门话题列表
     */
    @GetMapping("/user/hotquestionlist")
    public Result getHotQuestionList() {
        List<QuestionDto> hotQuestionDtoList = questionService.findHotQuestion();
        return Result.success(hotQuestionDtoList);
    }

    /**
     * 获取根据内容查询（模糊查询）到的问题列表
     */
    @GetMapping("/user/searchQuestionlist")
    public Result getSearchQuestionList(@RequestParam(name = "searchText") String searchText) {
        List<QuestionDto> searchQuestionDtoList = questionService.findSearchQuestion(searchText);
        return Result.success(searchQuestionDtoList);
    }

    /**
     * 根据session获取当前登录用户的用户名
     */
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

    /**
     * 将用户信息存储在session中
     */
    @PutMapping("/user/setuserinfo")
    public Result setUserInfoInSession(@RequestBody User user, HttpServletRequest request) {
        request.getSession().setAttribute("userInfo", user);
        return Result.success();
    }
}
