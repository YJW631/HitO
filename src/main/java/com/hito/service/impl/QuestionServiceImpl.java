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

    /**
     * 查询所用问题（审核通过状态）
     */
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

    /**
     * 添加新问题
     */
    @Override
    public void addNewQuestion(Question question) {
        question.setCreateTime(LocalDateTime.now());
        question.setUpdateTime(LocalDateTime.now());
        questionMapper.create(question);
    }

    /**
     * 查询问题（审核通过状态）总数
     */
    @Override
    public Integer count() {
        return questionMapper.queryCount();
    }

    /**
     * 查询username下所有问题
     */
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

    /**
     * 查询username下问题总数
     */
    @Override
    public Integer countMyQuestion(String username) {
        return questionMapper.queryMyCount(username);
    }

    /**
     * 根据问题id删除问题
     */
    @Override
    public void deleteById(Integer id) {
        questionMapper.delete(id);
    }

    /**
     * 根据问题id查询问题
     */
    @Override
    public QuestionDto findQuestionById(Integer id) {
        Question question=questionMapper.findById(id);
        User user = userMapper.findByUsername(question.getCreator());
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        questionDto.setUser(user);
        return questionDto;
    }

    /**
     * 问题点赞
     */
    @Override
    public void like(Integer id, Integer likeCount) {
        questionMapper.updateLikeCount(id,likeCount);
    }

    /**
     * 问题浏览
     */
    @Override
    public void view(Integer id) {
        questionMapper.updateViewCount(id);
    }

    /**
     * 查询当前热点问题列表
     */
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

    /**
     * 根据查询信息查询所有符合条件的问题
     */
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

    /**
     * 评论数+1
     */
    @Override
    public void addCommentCount(Integer ascription) {
        questionMapper.addCommentCount(ascription);
    }

    /**
     * 评论数-1
     */
    @Override
    public void subCommentCount(Integer ascription) {
        questionMapper.subCommentCount(ascription);
    }

    /**
     * 查询所有问题（所有状态）
     */
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

    /**
     * 问题审核通过
     */
    @Override
    public void pass(Integer id) {
        questionMapper.pass(id);
    }

    /**
     * 问题不再显示
     */
    @Override
    public void unPass(Integer id) {
        questionMapper.unPass(id);
    }

    /**
     * 查询问题（所有状态）总数
     */
    @Override
    public Integer administratorCount() {
        return questionMapper.queryAllCount();
    }

    /**
     * 根据标签查询问题（审核通过状态）
     */
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

    /**
     * 根据标签查询问题（审核通过状态）总数
     */
    @Override
    public Integer countByTag(Integer tag) {
        return questionMapper.queryCountByTag(tag);
    }

    /**
     * 取消问题的有最新回复状态
     */
    @Override
    public void deleteNew(Integer id) {
        questionMapper.deleteNew(id);
    }

    /**
     * 查询username下所有有最新回复的问题
     */
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

    /**
     * 查询username下有最新回复的问题总数
     */
    @Override
    public Integer countMyQuestionWithNewComment(String username) {
        return questionMapper.queryMyCountWithNewComment(username);
    }

    /**
     * 查询问题（所有状态）总数
     */
    @Override
    public Integer findQuestionNumberOfAnyStatus() {
        return questionMapper.queryAllCount();
    }

    /**
     * 按内容查询问题（所有状态）总数
     */
    @Override
    public Integer findQuestionNumberOfAnyStatusByContent(String content) {
        List<Question> searchQuestionList = questionMapper.findAllSearchQuestion(content);
        return searchQuestionList.size();
    }

    /**
     * 按内容查询问题（所有状态）
     */
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
