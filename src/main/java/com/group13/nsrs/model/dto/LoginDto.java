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
@ApiModel(value = "初始登录页面",description = "初始登录页面")
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @ApiModelProperty(value = "账号/学号")
    private String snumber;
    @ApiModelProperty(value = "初始密码(123456)")
    private String password;
}
