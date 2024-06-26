package com.hito.service.impl;

import com.hito.dto.QuestionDto;
import com.hito.mapper.QuestionMapper;
import com.hito.mapper.UserMapper;
import com.hito.service.QuestionService;
import com.hito.vo.Question;
import com.hito.vo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<QuestionDto> findAllQuestion(Integer pageNumber, Integer pageSize) {
        List<Question> questionList = questionMapper.findAll((pageNumber - 1) * pageSize, pageSize);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findByUsername(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    @Override
    public void addNewQuestion(Question question) {
        question.setCreateTime(LocalDateTime.now());
        question.setUpdateTime(LocalDateTime.now());
        questionMapper.create(question);
    }

    @Override
    public Integer count() {
        return questionMapper.queryCount();
    }

    @Override
    public List<QuestionDto> findMyQuestion(Integer pageNumber, Integer pageSize,String username) {
        List<Question> questionList = questionMapper.findMyQuestion((pageNumber - 1) * pageSize, pageSize,username);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findByUsername(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    @Override
    public Integer countMyQuestion(String username) {
        return questionMapper.queryMyCount(username);
    }

    @Override
    public void deleteById(Integer id) {
        questionMapper.delete(id);
    }

    @Override
    public QuestionDto findQuestionById(Integer id) {
        Question question=questionMapper.findById(id);
        User user = userMapper.findByUsername(question.getCreator());
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        questionDto.setUser(user);
        return questionDto;
    }

    @Override
    public void like(Integer id, Integer likeCount) {
        questionMapper.updateLikeCount(id,likeCount);
    }

    @Override
    public void view(Integer id) {
        questionMapper.updateViewCount(id);
    }

    @Override
    public List<QuestionDto> findHotQuestion() {
        List<Question> hotQuestionList = questionMapper.findAllHotQuestion();
        List<QuestionDto> hotQuestionDtoList = new ArrayList<>();
        for (Question question : hotQuestionList) {
            User user = userMapper.findByUsername(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            hotQuestionDtoList.add(questionDto);
        }
        return hotQuestionDtoList;
    }

    @Override
    public List<QuestionDto> findSearchQuestion(String searchText) {
        List<Question> searchQuestionList = questionMapper.findAllSearchQuestion(searchText);
        List<QuestionDto> searchQuestionDtoList = new ArrayList<>();
        for (Question question : searchQuestionList) {
            User user = userMapper.findByUsername(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            searchQuestionDtoList.add(questionDto);
        }
        return searchQuestionDtoList;
    }

    @Override
    public void addCommentCount(Integer ascription) {
        questionMapper.addCommentCount(ascription);
    }

    @Override
    public void subCommentCount(Integer ascription) {
        questionMapper.subCommentCount(ascription);
    }

    @Override
    public List<QuestionDto> findAllQuestionOfAnyStatus(Integer pageNumber, Integer pageSize) {
        List<Question> questionList = questionMapper.findAllOfAnyStatus((pageNumber - 1) * pageSize, pageSize);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findByUsername(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    @Override
    public void pass(Integer id) {
        questionMapper.pass(id);
    }

    @Override
    public void unPass(Integer id) {
        questionMapper.unPass(id);
    }

    @Override
    public Integer administratorCount() {
        return questionMapper.queryAllCount();
    }

    @Override
    public List<QuestionDto> findQuestionByTag(Integer pageNumber, Integer pageSize, Integer tag) {
        List<Question> questionList = questionMapper.findByTag((pageNumber - 1) * pageSize, pageSize,tag);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findByUsername(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    @Override
    public Integer countByTag(Integer tag) {
        return questionMapper.queryCountByTag(tag);
    }

    @Override
    public void deleteNew(Integer id) {
        questionMapper.deleteNew(id);
    }

    @Override
    public List<QuestionDto> findMyQuestionWithNewComment(Integer pageNumber, Integer pageSize, String username) {
        List<Question> questionList = questionMapper.findMyQuestionWithNewComment((pageNumber - 1) * pageSize, pageSize,username);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findByUsername(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    @Override
    public Integer countMyQuestionWithNewComment(String username) {
        return questionMapper.queryMyCountWithNewComment(username);
    }

    @Override
    public Integer findQuestionNumberOfAnyStatus() {
        return questionMapper.queryAllCount();
    }

    @Override
    public Integer findQuestionNumberOfAnyStatusByContent(String content) {
        List<Question> searchQuestionList = questionMapper.findAllSearchQuestion(content);
        return searchQuestionList.size();
    }

    @Override
    public List<QuestionDto> findAllQuestionOfAnyStatusByContent(String content) {
        List<Question> searchQuestionList = questionMapper.findAllSearchQuestion(content);
        List<QuestionDto> searchQuestionDtoList = new ArrayList<>();
        for (Question question : searchQuestionList) {
            User user = userMapper.findByUsername(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            searchQuestionDtoList.add(questionDto);
        }
        return searchQuestionDtoList;
    }
}
