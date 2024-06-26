package com.hito.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Integer tag;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String creator;
    private Integer status;
    private Integer haveNew;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
