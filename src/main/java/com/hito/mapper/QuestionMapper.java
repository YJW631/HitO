package com.hito.mapper;

import com.hito.dto.QuestionDto;
import com.hito.vo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,tag,creator,create_time,update_time) values (#{title},#{description},#{tag},#{creator},#{createTime},#{updateTime});")
    void create(Question question);

    @Select("select * from question where status=1 order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findAll(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from question where status=1;")
    Integer queryCount();

    @Select("select * from question where creator=#{username} and status=1 order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findMyQuestion(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("username") String username);

    @Select("select count(*) from question where creator=#{username} and status=1;")
    Integer queryMyCount(String username);

    @Update("update question set status=0 where id=#{id};")
    void delete(Integer id);

    @Select("select * from question where id=#{id};")
    Question findById(Integer id);

    @Update("update question set like_count=#{likeCount} where id=#{id};")
    void updateLikeCount(@Param("id") Integer id, @Param("likeCount") Integer likeCount);

    @Update("update question set view_count=view_count+1 where id=#{id};")
    void updateViewCount(Integer id);

    @Select("select * from question where status=1 order by view_count desc limit 0,5;")
    List<Question> findAllHotQuestion();

    @Select("select * from question where title regexp #{searchText} order by create_time desc;")
    List<Question> findAllSearchQuestion(String searchText);

    @Update("update question set comment_count=comment_count+1 where id=#{ascription};")
    void addCommentCount(Integer ascription);

    @Update("update question set comment_count=comment_count-1 where id=#{ascription};")
    void subCommentCount(Integer ascription);


    @Select("select * from question where status=1 or status=2 order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findAllOfAnyStatus(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Update("update question set status=1 where id=#{id};")
    void pass(Integer id);

    @Update("update question set status=2 where id=#{id};")
    void unPass(Integer id);

    @Select("select count(*) from question where status = 1 or status =2;")
    Integer queryAllCount();

    @Select("select * from question where status=1 and tag=#{tag} order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findByTag(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("tag") Integer tag);

    @Select("select count(*) from question where status=1 and tag =#{tag};")
    Integer queryCountByTag(Integer tag);

    @Update("update question set have_new=1 where id=#{id};")
    void haveNew(Integer id);

    @Update("update question set have_new=0 where id=#{id};")
    void deleteNew(Integer id);

    @Select("select * from question where creator=#{username} and status=1 and have_new=1 order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findMyQuestionWithNewComment(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("username") String username);

    @Select("select count(*) from question where creator=#{username} and status=1 and have_new=1;")
    Integer queryMyCountWithNewComment(String username);

    @Delete("delete from question where creator=#{username}")
    void deleteByCreator(String username);
}
