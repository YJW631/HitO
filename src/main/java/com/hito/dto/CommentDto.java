package com.hito.dto;

import com.hito.vo.User;
import lombok.Data;

import java.net.UnknownServiceException;
import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Integer id;
    private String content;
    private String creator;
    private Integer ascription;
    private Integer likeCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private User user;
}
