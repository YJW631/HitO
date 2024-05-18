package com.hito.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String creator;
    private Long createTime;
    private Long updateTime;
}
