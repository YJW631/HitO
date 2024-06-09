package com.hito.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String selfIntroduction;
    private String token;
    private String avatarUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
