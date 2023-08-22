package com.group13.nsrs.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fu Qiujie
 * @since 2023/8/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HotArticleVo extends ArticleVo{
    /**
     * 文章分值
     */
    private Integer score;
}
