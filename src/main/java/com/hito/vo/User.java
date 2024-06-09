package com.hito.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private String bio;
    private Long createTime;
    private Long updateTime;

}
