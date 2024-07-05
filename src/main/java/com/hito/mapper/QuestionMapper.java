package com.hito.mapper;

import com.hito.vo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    /**
     * 增加一个问题记录
     */
    @Insert("insert into question (title,description,tag,creator,create_time,update_time) values (#{title},#{description},#{tag},#{creator},#{createTime},#{updateTime});")
    void create(Question question);

    /**
     * 查询所有问题（审核通过状态）
     */
    @Select("select * from question where status=1 order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findAll(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    /**
     * 查询问题（审核通过状态）总数
     */
    @Select("select count(*) from question where status=1;")
    Integer queryCount();

    /**
     * 查询username下所有问题
     */
    @Select("select * from question where creator=#{username} and status=1 order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findMyQuestion(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("username") String username);

    /**
     * 查询username下问题总数
     */
    @Select("select count(*) from question where creator=#{username} and status=1;")
    Integer queryMyCount(String username);

    /**
     * 根据问题id删除一条问题
     */
    @Update("update question set status=0 where id=#{id};")
    void delete(Integer id);

    /**
     * 根据问题id查询一条问题
     */
    @Select("select * from question where id=#{id};")
    Question findById(Integer id);

    /**
     * 更新问题的点赞数
     */
    @Update("update question set like_count=#{likeCount} where id=#{id};")
    void updateLikeCount(@Param("id") Integer id, @Param("likeCount") Integer likeCount);

    /**
     * 更新问题的浏览数
     */
    @Update("update question set view_count=view_count+1 where id=#{id};")
    void updateViewCount(Integer id);

    /**
     * 查询当前最热问题
     */
    @Select("select * from question where status=1 order by view_count desc limit 0,5;")
    List<Question> findAllHotQuestion();

    /**
     * 根据内容模糊查询出所有符合条件的问题
     */
    @Select("select * from question where title regexp #{searchText} order by create_time desc;")
    List<Question> findAllSearchQuestion(String searchText);

    /**
     * 更新问题的评论数，增加
     */
    @Update("update question set comment_count=comment_count+1 where id=#{ascription};")
    void addCommentCount(Integer ascription);

    /**
     * 更新问题的评论数，减少
     */
    @Update("update question set comment_count=comment_count-1 where id=#{ascription};")
    void subCommentCount(Integer ascription);

    /**
     * 查询所有问题（所有状态）
     */
    @Select("select * from question where status=1 or status=2 order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findAllOfAnyStatus(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    /**
     * 修改问题状态（审核通过）
     */
    @Update("update question set status=1 where id=#{id};")
    void pass(Integer id);

    /**
     * 修改问题状态（不再展示）
     */
    @Update("update question set status=2 where id=#{id};")
    void unPass(Integer id);

    /**
     * 查询问题（所有状态）总数
     */
    @Select("select count(*) from question where status = 1 or status =2;")
    Integer queryAllCount();

    /**
     * 根据标签查询问题（审核通过状态）
     */
    @Select("select * from question where status=1 and tag=#{tag} order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findByTag(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("tag") Integer tag);

    /**
     * 根据标签查询问题（审核通过状态）数量
     */
    @Select("select count(*) from question where status=1 and tag =#{tag};")
    Integer queryCountByTag(Integer tag);

    /**
     * 修改问题有无最新消息的状态（有最新消息）
     */
    @Update("update question set have_new=1 where id=#{id};")
    void haveNew(Integer id);

    /**
     * 修改问题有无最新消息的状态（无最新消息）
     */
    @Update("update question set have_new=0 where id=#{id};")
    void deleteNew(Integer id);

    /**
     * 查询username下所有有最新回复的问题
     */
    @Select("select * from question where creator=#{username} and status=1 and have_new=1 order by create_time desc limit #{offset},#{pageSize};")
    List<Question> findMyQuestionWithNewComment(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("username") String username);

    /**
     * 查询username下有最新回复问题的总数
     */
    @Select("select count(*) from question where creator=#{username} and status=1 and have_new=1;")
    Integer queryMyCountWithNewComment(String username);

    /**
     * 删除username下的所用问题
     */
    @Delete("delete from question where creator=#{username}")
    void deleteByCreator(String username);
}
