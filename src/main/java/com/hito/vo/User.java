package com.hito.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 个人介绍
     */
    private String selfIntroduction;

    /**
     * token
     */
    private String token;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最近更新时间
     */
    private LocalDateTime updateTime;

}
