package com.hito.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer id;
    private String content;
    private String creator;
    private Integer ascription;
    private Integer likeCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
