package com.group13.nsrs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@Data
@ApiModel(value = "文章发布数据传输对象", description = "用于朋友圈文章发布的数据传输对象")
public class ArticleDto {
    @ApiModelProperty(value = "文章标题", required = true)
    private String title;
    @ApiModelProperty(value = "文本内容", required = true)
    private String content;
    @ApiModelProperty(value = "文章标签，多个以逗号分割")
    private String labels;
    @ApiModelProperty(value = "上传的图片，多个以逗号分割")
    private String images;
}
