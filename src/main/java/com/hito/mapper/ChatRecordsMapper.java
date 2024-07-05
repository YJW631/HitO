package com.hito.mapper;

import com.hito.vo.ChatRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Entity com.hito.vo.ChatRecords
 */
@Mapper
public interface ChatRecordsMapper {


    /**
     * 增加一条聊天记录
     */
    int insert(ChatRecords record);

    /**
     * 查询两个用户间的所用历史聊天记录
     */
    @Select("SELECT * FROM `chat_records`\n" +
            "WHERE (send_to = #{from} AND from_by = #{sendTo}) OR (send_to = #{sendTo} AND from_by = #{from})\n" +
            "ORDER BY `sent_at` asc;")
    List<ChatRecords> selectAllByFromAddSendTo(@Param("from") String from, @Param("sendTo") String sendTo);
}
