package com.hito.controller;

import com.hito.mapper.ChatRecordsMapper;
import com.hito.pojo.Result;
import com.hito.vo.ChatRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatRecords")
public class ChatRecordsController {
    @Autowired
    private ChatRecordsMapper chatRecordsMapper;

    /**
     * 获取聊天记录
     */
    @RequestMapping("list")
    public Result list(String from, String sendTo) {
        List<ChatRecords> chatRecords =
                chatRecordsMapper.selectAllByFromAddSendTo(from, sendTo);
        return Result.success(chatRecords);
    }
}
