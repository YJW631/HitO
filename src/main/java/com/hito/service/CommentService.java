package com.hito.service;

import com.hito.dto.CommentDto;
import com.hito.vo.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 添加评论
     */
    void addComment(Comment comment);

    /**
     * 查询相应问题下的所有评论
     */
    List<CommentDto> queryComment(Integer ascription, Integer pageNumber, Integer pageSize);

    /**
     * 获取相应问题下的评论数
     */
    Integer getCommentNumber(Integer ascription);

    /**
     * 点赞
     */
    void like(Integer id, Integer likeCount);

    /**
     * 根据id删除评论
     */
    void deleteById(Integer id);
}
