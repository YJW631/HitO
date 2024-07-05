package com.hito.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Question {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题详细描述
     */
    private String description;

    /**
     * 问题标签
     */
    private Integer tag;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 浏览数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 问题发布者用户名
     */
    private String creator;

    /**
     * 问题状态
     */
    private Integer status;

    /**
     * 是否有最新回复
     */
    private Integer haveNew;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最近更新时间
     */
    private LocalDateTime updateTime;
}
