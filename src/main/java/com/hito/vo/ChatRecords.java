package com.hito.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName chat_records
 */
@Data
public class ChatRecords implements Serializable {
    /**
     * 
     */
    private Integer chatId;

    /**
     * 
     */
    private String sendTo;

    /**
     * 
     */
    private String fromBy;

    /**
     * 
     */
    private String message;

    /**
     * 
     */
    private String sentAt;


    private static final long serialVersionUID = 1L;


}