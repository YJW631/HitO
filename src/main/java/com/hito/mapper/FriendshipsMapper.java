package com.hito.mapper;

import com.hito.vo.Friendships;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @Entity com.hito.vo.Friendships
 */
@Mapper
public interface FriendshipsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Friendships record);

    int insertSelective(Friendships record);

    Friendships selectByPrimaryKey(Long id);

    List<Friendships> selectAllByUserId(Integer userId);

    int updateByPrimaryKeySelective(Friendships record);

    int updateByPrimaryKey(Friendships record);


    @Update("UPDATE friendships SET status = 'accepted'\n" +
            "    WHERE (friend_id, user_id) = (#{friendId}, #{userId});")
    int updateFriend(@Param("friendId") Integer friendId, @Param("userId") Integer userId, @Param("status") String status);


    @Select("SELECT COUNT(1)\n" +
            "FROM friendships\n" +
            "WHERE ( (friend_id = #{friendId} AND user_id = #{userId})\n" +
            "   OR (friend_id = #{userId} AND user_id = #{friendId}) )\n" +
            "AND status = #{status};\n")
    int getByStatus(@Param("friendId") Integer friendId, @Param("userId") Integer userId, @Param("status") String status);

    @Select("SELECT *\n" +
            "FROM friendships\n" +
            "WHERE (friend_id = #{friendId} AND user_id = #{userId})\n" +
            "   OR (friend_id = #{userId} AND user_id = #{friendId}) \n")
    List<Friendships> get(@Param("friendId") Integer friendId, @Param("userId") Integer userId);

    @Select("select friend_id from friendships where user_id=#{id} and status='accepted';")
    List<Integer> selectIdById(Integer id);

    @Delete("delete from friendships WHERE (friend_id = #{id1} AND user_id = #{id2}) OR (friend_id = #{id2} AND user_id = #{id1});")
    void delete(@Param("id1") Integer id1, @Param("id2") Integer id2);
}
