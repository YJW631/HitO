package com.hito.controller;

import com.hito.dto.CommentDto;
import com.hito.dto.QuestionDto;
import com.hito.pojo.Result;
import com.hito.service.CommentService;
import com.hito.service.QuestionService;
import com.hito.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    /**
     *获取问题id对应的问题对象以及相应的问题发布者的用户对象
     * */
    @GetMapping("/user/questionbyid")
    public Result getQuestionList(@RequestParam(name = "id") Integer id) {
        QuestionDto questionDto = questionService.findQuestionById(id);
        return Result.success(questionDto);
    }

    /**
     *修改问题点赞数
     * */
    @PutMapping("/user/like")
    public Result changeLike(@RequestBody Map<String, String> changeInfo) {
        Integer id = Integer.valueOf(changeInfo.get("id"));
        Integer likeCount = Integer.valueOf(changeInfo.get("likeCount"));
        questionService.like(id, likeCount);
        return Result.success();
    }

    /**
     *发表评论
     * */
    @PostMapping("/user/comment")
    public Result comment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        questionService.addCommentCount(comment.getAscription());
        return Result.success();
    }

    /**
     *获取评论列表
     * */
    @GetMapping("/user/commentlist")
    public Result getCommentList(@RequestParam(name = "ascription") Integer ascription,
                                 @RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<CommentDto> commentDtoList = commentService.queryComment(ascription, pageNumber, pageSize);
        return Result.success(commentDtoList);
    }

    /**
     *获取评论总数
     * */
    @GetMapping("/user/commentnumber")
    public Result getCommentNumber(@RequestParam(name = "ascription") Integer ascription) {
        return Result.success(commentService.getCommentNumber(ascription));
    }

    /**
     *修改评论点赞数
     * */
    @PutMapping("/user/commentlike")
    public Result changeCommentLike(@RequestBody Map<String, String> changeInfo) {
        Integer id = Integer.valueOf(changeInfo.get("id"));
        Integer likeCount = Integer.valueOf(changeInfo.get("likeCount"));
        commentService.like(id, likeCount);
        return Result.success();
    }

    /**
     *根据问题id删除问题
     * */
    @DeleteMapping("/user/delete")
    public Result deleteById(@RequestParam(name = "id") Integer id) {
        questionService.deleteById(id);
        return Result.success("删除成功");
    }

    /**
     *根据评论id删除评论
     * */
    @DeleteMapping("/user/deletecomment")
    public Result deleteCommentById(@RequestParam(name = "id") Integer id,
                                    @RequestParam(name = "qid") Integer qid) {
        questionService.subCommentCount(qid);
        commentService.deleteById(id);
        return Result.success("删除成功");
    }
}
