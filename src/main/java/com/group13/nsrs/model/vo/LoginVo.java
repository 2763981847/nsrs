package com.group13.nsrs.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "登录返回数据", description = "登录成功后返回的数据")
public class LoginVo extends UserVo {
    @ApiModelProperty(value = "含有用户基本信息的token")
    private String token;
}
