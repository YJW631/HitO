package com.hito.controller;

import com.hito.dto.QuestionDto;
import com.hito.pojo.Result;
import com.hito.service.QuestionService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/user/myquestionlist")
    public Result getQuestionList(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "username")String username) {
        List<QuestionDto> questionDtoList = questionService.findMyQuestion(pageNumber, pageSize,username);
        return Result.success(questionDtoList);
    }

    @GetMapping("/user/myquestionnumber")
    public Result getQuestionNumber(@RequestParam(name = "username")String username) {
        Integer questionNumber=questionService.countMyQuestion(username);
        return Result.success(questionNumber);
    }

    @GetMapping("/user/get/username")
    public Result getNameBySession(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return Result.success(user.getUsername());
    }



}
