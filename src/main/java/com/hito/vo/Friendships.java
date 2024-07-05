package com.hito.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Friendships implements Serializable {
    /**
     * 好友一方id
     */
    private Integer friendId;

    /**
     * 好友另一方id
     */
    private Integer userId;

    /**
     * 好友关系申请者id
     */
    private Integer initiator;

    /**
     * 状态
     */
    private Object status;

    /**
     * 创建时间
     */
    private Date createdAt;

    private static final long serialVersionUID = 1L;

    private String username;
}