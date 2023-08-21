package com.group13.nsrs.model.vo;

import com.group13.nsrs.model.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.checkerframework.checker.units.qual.C;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentVo extends Comment {
    private String userName;
    private String userAvatar;
}
