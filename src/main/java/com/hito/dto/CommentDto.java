package com.hito.dto;

import com.hito.vo.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论发表者用户名
     */
    private String creator;

    /**
     * 所属问题id
     */
    private Integer ascription;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最近更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 发表评论的用户的信息
     */
    private User user;
}
