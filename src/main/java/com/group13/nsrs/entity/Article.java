package com.group13.nsrs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 文章id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 作者id
     */
    @TableField(value = "author_id")
    private Long authorId;

    /**
     * 作者昵称
     */
    @TableField(value = "author_name")
    private String authorName;

    /**
     * 文章图片，多张逗号分隔
     */
    @TableField(value = "images")
    private String images;

    /**
     * 文章标签，多个以逗号分割
     */
    @TableField(value = "labels")
    private String labels;

    /**
     * 点赞数
     */
    @TableField(value = "likes")
    private Integer likes;

    /**
     * 收藏数
     */
    @TableField(value = "collection")
    private Integer collection;

    /**
     * 评论数
     */
    @TableField(value = "comment")
    private Integer comment;

    /**
     * 阅读数
     */
    @TableField(value = "views")
    private Integer views;

    /**
     * 文章内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private LocalDateTime createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}