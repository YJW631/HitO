package com.hito.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatRecords implements Serializable {
    /**
     * 主键
     */
    private Integer chatId;

    /**
     * 发送方名字
     */
    private String sendTo;

    /**
     * 接收方名字
     */
    private String fromBy;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 发送时间
     */
    private String sentAt;

    private static final long serialVersionUID = 1L;
}