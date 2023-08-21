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
@ApiModel(value = "注册用数据传输对象", description = "注册信息")
public class RegisterDto {
    @ApiModelProperty(value = "账号(学号)", required = true)
    private String snumber;
    @ApiModelProperty(value = "初始化密码", required = true)
    private String password;
    @ApiModelProperty(value = "电话号码", required = true)
    private String phone;
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
}
