package com.hito.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName friendships
 */
@Data
public class Friendships implements Serializable {
    /**
     * 
     */
    private Integer friendId;

    /**
     * 
     */
    private Integer userId;

    private Integer initiator;

    /**
     * 
     */
    private Object status;

    /**
     * 
     */
    private Date createdAt;

    private static final long serialVersionUID = 1L;

    private String username;


}