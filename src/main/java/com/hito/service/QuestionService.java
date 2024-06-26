package com.hito.service;

import com.hito.dto.QuestionDto;
import com.hito.vo.Question;

import java.util.List;

public interface QuestionService {

    List<QuestionDto> findAllQuestion(Integer pageNumber, Integer pageSize);

    void addNewQuestion(Question question);

    Integer count();

    List<QuestionDto> findMyQuestion(Integer pageNumber, Integer pageSize,String useranme);

    Integer countMyQuestion(String username);

    void deleteById(Integer id);

    QuestionDto findQuestionById(Integer id);

    void like(Integer id, Integer likeCount);

    void view(Integer id);

    List<QuestionDto> findHotQuestion();

    List<QuestionDto> findSearchQuestion(String searchText);

    void addCommentCount(Integer ascription);

    void subCommentCount(Integer qid);

    List<QuestionDto> findAllQuestionOfAnyStatus(Integer pageNumber, Integer pageSize);

    void pass(Integer id);

    void unPass(Integer id);

    Integer administratorCount();

    List<QuestionDto> findQuestionByTag(Integer pageNumber, Integer pageSize, Integer tag);

    Integer countByTag(Integer tag);

    void deleteNew(Integer id);

    List<QuestionDto> findMyQuestionWithNewComment(Integer pageNumber, Integer pageSize, String username);

    Integer countMyQuestionWithNewComment(String username);
}
