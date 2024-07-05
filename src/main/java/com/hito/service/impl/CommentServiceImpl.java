package com.hito.service.impl;

import com.hito.dto.CommentDto;
import com.hito.mapper.CommentMapper;
import com.hito.mapper.QuestionMapper;
import com.hito.mapper.UserMapper;
import com.hito.service.CommentService;
import com.hito.vo.Comment;
import com.hito.vo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 添加评论
     */
    @Override
    public void addComment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        commentMapper.insert(comment);
        questionMapper.haveNew(comment.getAscription());
    }

    /**
     * 查询相应问题下的所有评论
     */
    @Override
    public List<CommentDto> queryComment(Integer ascription, Integer pageNumber, Integer pageSize) {
        List<Comment> commentList = commentMapper.findCommentByAscription(ascription, (pageNumber - 1) * pageSize, pageSize);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            User user = userMapper.findByUsername(comment.getCreator());
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(user);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    /**
     * 获取相应问题下的评论数
     */
    @Override
    public Integer getCommentNumber(Integer ascription) {
        return commentMapper.countByAscription(ascription);
    }

    /**
     * 点赞
     */
    @Override
    public void like(Integer id, Integer likeCount) {
        commentMapper.updateLikeCount(id, likeCount);
    }

    /**
     * 根据id删除评论
     */
    @Override
    public void deleteById(Integer id) {
        commentMapper.deleteById(id);
    }
}
