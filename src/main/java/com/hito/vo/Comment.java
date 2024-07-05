package com.hito.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    /**
     *主键
     */
    private Integer id;

    /**
     *评论内容
     */
    private String content;

    /**
     *评论发表者用户名
     */
    private String creator;

    /**
     *评论所属问题id
     */
    private Integer ascription;

    /**
     *点赞数
     */
    private Integer likeCount;

    /**
     *创建时间
     */
    private LocalDateTime createTime;

    /**
     *最近更新时间
     */
    private LocalDateTime updateTime;
}
