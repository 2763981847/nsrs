package com.group13.nsrs.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Resource;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@Data
@ApiModel(value = "用户基本信息", description = "脱敏后的用户基本信息")
public class UserVo {
    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "昵称")
    private String name;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "头像url")
    private String avatar;
    @ApiModelProperty(value = "性别 0 - 男 1 - 女 2 - 未知")
    private Integer sex;
    @ApiModelProperty(value = "学号")
    private String snumber;
    @ApiModelProperty(value = "用户身份")
    private Integer role;
    @ApiModelProperty(value = "兴趣爱好，多个爱好用逗号隔开")
    private String hobbies;
}
