package com.hito.controller;

import com.hito.dto.QuestionDto;
import com.hito.pojo.Result;
import com.hito.service.QuestionService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/user/myquestionlistwnc")
    public Result getQuestionListWithNewComment(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "username")String username) {
        List<QuestionDto> questionDtoList = questionService.findMyQuestionWithNewComment(pageNumber, pageSize,username);
        return Result.success(questionDtoList);
    }

    @GetMapping("/user/myquestionnumber")
    public Result getQuestionNumber(@RequestParam(name = "username")String username) {
        Integer questionNumber=questionService.countMyQuestion(username);
        return Result.success(questionNumber);
    }

    @GetMapping("/user/myquestionnumberwnc")
    public Result getQuestionNumberWithNewComment(@RequestParam(name = "username")String username) {
        Integer questionNumber=questionService.countMyQuestionWithNewComment(username);
        return Result.success(questionNumber);
    }

    @GetMapping("/user/get/username")
    public Result getNameBySession(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return Result.success(user.getUsername());
    }

    @PutMapping("/user/denew")
    public Result deleteNewStatus(@RequestBody Map<String, String> changeInfo){
        Integer id = Integer.valueOf(changeInfo.get("id"));
        questionService.deleteNew(id);
        return Result.success();
    }


}
