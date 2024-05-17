package com.hito.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long createTime;
    private long updateTime;

}
