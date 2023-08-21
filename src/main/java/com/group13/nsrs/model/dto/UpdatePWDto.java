package com.group13.nsrs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@Data
@ApiModel(value = "修改密码数据传输对象")
public class UpdatePWDto {
    @ApiModelProperty(value = "用户id(找回密码时需要，修改密码时不需要)")
    private Long userId;
    @ApiModelProperty(value = "旧密码(修改密码时需要，找回密码时不需要)")
    private String oldPassword;
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}
