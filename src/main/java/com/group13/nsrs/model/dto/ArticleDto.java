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
@ApiModel(value = "朋友圈",description = "朋友圈信息")
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    @ApiModelProperty(value = "文章标题")
    private String title;
    @ApiModelProperty(value = "文本内容")
    private String content;
    @ApiModelProperty(value = "文章标签")
    private String labels;
    @ApiModelProperty(value = "上传的图片")
    private String images;
}
