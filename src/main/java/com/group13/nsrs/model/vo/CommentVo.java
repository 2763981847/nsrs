package com.group13.nsrs.model.vo;

import com.group13.nsrs.model.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.checkerframework.checker.units.qual.C;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "文章评论数据视图对象")
public class CommentVo extends Comment {
    @ApiModelProperty("评论人昵称")
    private String userName;
    @ApiModelProperty("评论人头像")
    private String userAvatar;
}
