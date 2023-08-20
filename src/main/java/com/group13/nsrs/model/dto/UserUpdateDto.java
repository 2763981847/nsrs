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
@ApiModel(value = "个人中心修改信息", description = "个人信息修改信息")
public class UserUpdateDto {
    @ApiModelProperty(value = "用户昵称")
    private String name;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "所属学院")
    private String college;
    @ApiModelProperty(value = "用户性别 0:男 1:女 2:未知")
    private Integer sex;
}
