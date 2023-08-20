package com.group13.nsrs.model.dto;

import lombok.Data;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@Data
public class UpdatePWDto {

    private Long userId;
    private String oldPassword;
    private String newPassword;
}
