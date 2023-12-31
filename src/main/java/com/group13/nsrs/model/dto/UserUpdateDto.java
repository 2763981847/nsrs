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
@ApiModel(value = "用户信息修改数据传输对象")
public class UserUpdateDto {
    @ApiModelProperty(value = "用户昵称")
    private String name;
    @ApiModelProperty(value = "头像url")
    private String avatar;
    @ApiModelProperty(value = "所属学院")
    private String college;
    @ApiModelProperty(value = "用户性别 0:男 1:女 2:未知")
    private Integer sex;

    @ApiModelProperty(value = "兴趣爱好，多个爱好用逗号隔开")
    private String hobbies;
}
