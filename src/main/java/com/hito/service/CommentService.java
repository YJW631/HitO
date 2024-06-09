package com.hito.service;

import com.hito.dto.CommentDto;
import com.hito.vo.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment);

    List<CommentDto> queryComment(Integer ascription,Integer pageNumber,Integer pageSize);

    Integer getCommentNumber(Integer ascription);

    void like(Integer id, Integer likeCount);

    void deleteById(Integer id);
}
