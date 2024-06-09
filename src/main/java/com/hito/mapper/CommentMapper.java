package com.hito.mapper;

import com.hito.vo.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment (content, creator, ascription, create_time, update_time) VALUES (#{content},#{creator},#{ascription},#{createTime},#{updateTime});")
    void insert(Comment comment);

    @Select("select * from comment where ascription=#{ascription} order by create_time desc limit #{offset},#{pageSize};")
    List<Comment> findCommentByAscription(@Param("ascription") Integer ascription, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from comment where ascription=#{ascrition};")
    Integer countByAscription(Integer ascription);
    @Update("update comment set like_count=#{likeCount} where id=#{id};")
    void updateLikeCount(@Param("id") Integer id, @Param("likeCount") Integer likeCount);

    @Delete("delete from comment where id=#{id}")
    void deleteById(Integer id);
}
