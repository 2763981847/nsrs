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
@ApiModel(value = "第一次登录修改信息页面",description = "注册信息")
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @ApiModelProperty(value = "登录账号/学号")
    private String snumber;
    @ApiModelProperty(value = "初始化密码")
    private String password;
    @ApiModelProperty(value = "电话号码")
    private String phone;
    @ApiModelProperty(value = "验证码")
    private String code;
}
