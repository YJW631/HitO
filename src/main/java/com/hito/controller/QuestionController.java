package com.hito.controller;

import com.hito.dto.CommentDto;
import com.hito.dto.QuestionDto;
import com.hito.pojo.Result;
import com.hito.service.CommentService;
import com.hito.service.QuestionService;
import com.hito.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.callback.PrivateKeyCallback;
import javax.servlet.ServletRequest;
import javax.sound.sampled.AudioSystem;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/user/questionbyid")
    public Result getQuestionList(@RequestParam(name = "id") Integer id) {
        QuestionDto questionDto = questionService.findQuestionById(id);
        return Result.success(questionDto);
    }

    @PutMapping("/user/like")
    public Result changeLike(@RequestBody Map<String, String> changeInfo) {
        Integer id = Integer.valueOf(changeInfo.get("id"));
        Integer likeCount = Integer.valueOf(changeInfo.get("likeCount"));
        questionService.like(id, likeCount);
        return Result.success();
    }

    @PostMapping("/user/comment")
    public Result comment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        questionService.addCommentCount(comment.getAscription());
        return Result.success();
    }

    @GetMapping("/user/commentlist")
    public Result getCommentList(@RequestParam(name = "ascription") Integer ascription,
                                 @RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<CommentDto> commentDtoList = commentService.queryComment(ascription, pageNumber, pageSize);
        return Result.success(commentDtoList);
    }

    @GetMapping("/user/commentnumber")
    public Result getCommentNumber(@RequestParam(name = "ascription") Integer ascription) {
        return Result.success(commentService.getCommentNumber(ascription));
    }

    @PutMapping("/user/commentlike")
    public Result changeCommentLike(@RequestBody Map<String, String> changeInfo) {
        Integer id = Integer.valueOf(changeInfo.get("id"));
        Integer likeCount = Integer.valueOf(changeInfo.get("likeCount"));
        commentService.like(id, likeCount);
        return Result.success();
    }

    @DeleteMapping("/user/delete")
    public Result deleteById(@RequestParam(name = "id") Integer id) {
        questionService.deleteById(id);
        return Result.success("删除成功");
    }

    @DeleteMapping("/user/deletecomment")
    public Result deleteCommentById(@RequestParam(name = "id") Integer id,
                                    @RequestParam(name = "qid") Integer qid) {
        questionService.subCommentCount(qid);
        commentService.deleteById(id);
        return Result.success("删除成功");
    }


}
