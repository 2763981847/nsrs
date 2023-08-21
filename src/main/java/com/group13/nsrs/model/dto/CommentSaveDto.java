package com.group13.nsrs.model.dto;

import lombok.Data;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@Data
public class CommentSaveDto {

    private Long articleId;
    private String content;
}
