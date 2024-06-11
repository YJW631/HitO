package com.hito.dto;

import com.hito.vo.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private Integer tag;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String creator;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private User user;
}
