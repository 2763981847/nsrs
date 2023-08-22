package com.group13.nsrs.model.vo;

import com.group13.nsrs.model.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "文章视图对象", description = "用于朋友圈文章展示的视图对象")
public class ArticleVo extends Article {
    @ApiModelProperty(value = "作者昵称")
    private String authorName;
    @ApiModelProperty(value = "作者头像")
    private String authorAvatar;
    @ApiModelProperty(value = "是否点赞")
    private Boolean isLiked;
    @ApiModelProperty(value = "是否已收藏")
    private Boolean isCollected;
}
