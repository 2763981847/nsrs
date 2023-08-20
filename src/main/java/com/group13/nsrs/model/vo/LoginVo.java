package com.group13.nsrs.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginVo extends UserVo {
    private String token;
}
