package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Oreki
 * @TableName article
 */
@TableName(value = "article")
@Data
@ApiModel("文章实体类")
public class Article implements Serializable {
    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @TableField(value = "title")
    private String title;

    /**
     * 作者id
     */
    @ApiModelProperty(value = "作者id")
    @TableField(value = "author_id")
    private Long authorId;


    /**
     * 文章图片，多张逗号分隔
     */
    @ApiModelProperty(value = "文章图片，多张逗号分隔")
    @TableField(value = "images")
    private String images;

    /**
     * 文章标签，多个以逗号分割
     */
    @ApiModelProperty(value = "文章标签，多个以逗号分割")
    @TableField(value = "labels")
    private String labels;

    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数")
    @TableField(value = "likes")
    private Integer likes;

    /**
     * 收藏数
     */
    @ApiModelProperty(value = "收藏数")
    @TableField(value = "collection")
    private Integer collection;

    /**
     * 评论数
     */
    @ApiModelProperty(value = "评论数")
    @TableField(value = "comment")
    private Integer comment;

    /**
     * 阅读数
     */
    @ApiModelProperty(value = "阅读数")
    @TableField(value = "views")
    private Integer views;

    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容")
    @TableField(value = "content")
    private String content;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}