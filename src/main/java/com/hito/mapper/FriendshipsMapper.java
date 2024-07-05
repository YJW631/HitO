package com.hito.mapper;

import com.hito.vo.Friendships;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendshipsMapper {

    /**
     * 根据主键删除一个好友
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 增加一个好友记录
     */
    int insert(Friendships record);

    /**
     * 判断性增加一条好友记录
     */
    int insertSelective(Friendships record);

    /**
     * 根据主键查询一条好友记录
     */
    Friendships selectByPrimaryKey(Long id);

    /**
     * 查询用户id下的所有好友记录
     */
    List<Friendships> selectAllByUserId(Integer userId);

    /**
     * 判断行根据主键更新好友记录
     */
    int updateByPrimaryKeySelective(Friendships record);

    /**
     * 根据主键更新好友记录
     */
    int updateByPrimaryKey(Friendships record);

    /**
     * 更新号有记录的状态
     */
    @Update("UPDATE friendships SET status = 'accepted'\n" +
            "    WHERE (friend_id, user_id) = (#{friendId}, #{userId});")
    int updateFriend(@Param("friendId") Integer friendId, @Param("userId") Integer userId, @Param("status") String status);

    /**
     * 根据状态查询好友记录
     */
    @Select("SELECT COUNT(1)\n" +
            "FROM friendships\n" +
            "WHERE ( (friend_id = #{friendId} AND user_id = #{userId})\n" +
            "   OR (friend_id = #{userId} AND user_id = #{friendId}) )\n" +
            "AND status = #{status};\n")
    int getByStatus(@Param("friendId") Integer friendId, @Param("userId") Integer userId, @Param("status") String status);

    /**
     * 根据好友双方id查询好友记录
     */
    @Select("SELECT *\n" +
            "FROM friendships\n" +
            "WHERE (friend_id = #{friendId} AND user_id = #{userId})\n" +
            "   OR (friend_id = #{userId} AND user_id = #{friendId}) \n")
    List<Friendships> get(@Param("friendId") Integer friendId, @Param("userId") Integer userId);

    /**
     * 根据好友一方用户id查询另一方id
     */
    @Select("select friend_id from friendships where user_id=#{id} and status='accepted';")
    List<Integer> selectIdById(Integer id);

    /**
     * 根据好友双方id删除对应的好友记录
     */
    @Delete("delete from friendships WHERE (friend_id = #{id1} AND user_id = #{id2}) OR (friend_id = #{id2} AND user_id = #{id1});")
    void delete(@Param("id1") Integer id1, @Param("id2") Integer id2);
}
