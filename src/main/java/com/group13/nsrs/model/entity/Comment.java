package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
@Data
@ApiModel(value = "评论实体类")
public class Comment implements Serializable {
    /**
     * 评论id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "评论id")
    private Long id;

    /**
     * 评论人id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "评论人id")
    private Long userId;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    @ApiModelProperty(value = "文章id")
    private Long articleId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value = "评论内容")
    private String content;

    /**
     * 评论时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "评论时间")
    private LocalDateTime createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}