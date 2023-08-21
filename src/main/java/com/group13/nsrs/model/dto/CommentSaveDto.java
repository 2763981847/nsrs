package com.group13.nsrs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@Data
@ApiModel(value = "文章评论数据传输对象", description = "用于朋友圈文章评论的数据传输对象")
public class CommentSaveDto {
    @ApiModelProperty(value = "文章id", required = true)
    private Long articleId;
    @ApiModelProperty(value = "评论内容", required = true)
    private String content;
}
