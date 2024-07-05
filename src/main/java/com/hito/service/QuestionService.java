package com.hito.service;

import com.hito.dto.QuestionDto;
import com.hito.vo.Question;

import java.util.List;

public interface QuestionService {

    /**
     * 查询所用问题（审核通过状态）
     */
    List<QuestionDto> findAllQuestion(Integer pageNumber, Integer pageSize);

    /**
     * 添加新问题
     */
    void addNewQuestion(Question question);

    /**
     * 查询问题（审核通过状态）总数
     */
    Integer count();

    /**
     * 查询username下所有问题
     */
    List<QuestionDto> findMyQuestion(Integer pageNumber, Integer pageSize, String useranme);

    /**
     * 查询username下问题总数
     */
    Integer countMyQuestion(String username);

    /**
     * 根据问题id删除问题
     */
    void deleteById(Integer id);

    /**
     * 根据问题id查询问题
     */
    QuestionDto findQuestionById(Integer id);

    /**
     * 问题点赞
     */
    void like(Integer id, Integer likeCount);

    /**
     * 问题浏览
     */
    void view(Integer id);

    /**
     * 查询当前热点问题列表
     */
    List<QuestionDto> findHotQuestion();

    /**
     * 根据查询信息查询所有符合条件的问题
     */
    List<QuestionDto> findSearchQuestion(String searchText);

    /**
     * 评论数+1
     */
    void addCommentCount(Integer ascription);

    /**
     * 评论数-1
     */
    void subCommentCount(Integer qid);

    /**
     * 查询所有问题（所有状态）
     */
    List<QuestionDto> findAllQuestionOfAnyStatus(Integer pageNumber, Integer pageSize);

    /**
     * 问题审核通过
     */
    void pass(Integer id);

    /**
     * 问题不再显示
     */
    void unPass(Integer id);

    /**
     * 查询问题（所有状态）总数
     */
    Integer administratorCount();

    /**
     * 根据标签查询问题（审核通过状态）
     */
    List<QuestionDto> findQuestionByTag(Integer pageNumber, Integer pageSize, Integer tag);

    /**
     * 根据标签查询问题（审核通过状态）总数
     */
    Integer countByTag(Integer tag);

    /**
     * 取消问题的有最新回复状态
     */
    void deleteNew(Integer id);

    /**
     * 查询username下所有有最新回复的问题
     */
    List<QuestionDto> findMyQuestionWithNewComment(Integer pageNumber, Integer pageSize, String username);

    /**
     * 查询username下有最新回复的问题总数
     */
    Integer countMyQuestionWithNewComment(String username);

    /**
     * 查询问题（所有状态）总数
     */
    Integer findQuestionNumberOfAnyStatus();

    /**
     * 按内容查询问题（所有状态）总数
     */
    Integer findQuestionNumberOfAnyStatusByContent(String content);

    /**
     * 按内容查询问题（所有状态）
     */
    List<QuestionDto> findAllQuestionOfAnyStatusByContent(String content);
}
