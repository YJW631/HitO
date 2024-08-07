package com.hito.controller;

import com.hito.pojo.Result;
import com.hito.service.QuestionService;
import com.hito.vo.Question;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PublishController {

    @Autowired
    private QuestionService questionService;

    /**
     * 发布问题
     */
    @PostMapping("/user/publish")
    public Result publish(@RequestBody Question question, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        question.setCreator(user.getUsername());
        questionService.addNewQuestion(question);
        return Result.success();
    }
}
