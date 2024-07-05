package com.hito.mapper;

import com.hito.vo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 增加一条评论
     */
    @Insert("insert into comment (content, creator, ascription, create_time, update_time) VALUES (#{content},#{creator},#{ascription},#{createTime},#{updateTime});")
    void insert(Comment comment);

    /**
     * 查询问题id下所有评论
     */
    @Select("select * from comment where ascription=#{ascription} order by create_time desc limit #{offset},#{pageSize};")
    List<Comment> findCommentByAscription(@Param("ascription") Integer ascription, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    /**
     * 查询问题id下所有评论的数量
     */
    @Select("select count(*) from comment where ascription=#{ascrition};")
    Integer countByAscription(Integer ascription);

    /**
     * 修改评论的点赞数
     */
    @Update("update comment set like_count=#{likeCount} where id=#{id};")
    void updateLikeCount(@Param("id") Integer id, @Param("likeCount") Integer likeCount);

    /**
     * 根据id删除一条评论
     */
    @Delete("delete from comment where id=#{id}")
    void deleteById(Integer id);
}
