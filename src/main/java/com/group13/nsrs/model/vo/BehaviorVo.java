package com.group13.nsrs.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */

@Data
@ApiModel(value = "文章行为数据视图对象")
public class BehaviorVo {
    @ApiModelProperty("用户对文章的点赞状态")
    private Boolean isLiked;
    @ApiModelProperty("用户对文章的收藏状态")
    private Boolean isCollected;
}
