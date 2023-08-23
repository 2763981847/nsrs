package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
@ApiModel(value = "用户实体类")
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户id")
    private Long id;

    /**
     * 密码、通信等加密盐
     */
    @TableField(value = "salt")
    @ApiModelProperty(value = "密码、通信等加密盐")
    private String salt;

    /**
     * 用户名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 密码,md5加密
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "密码,md5加密")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 头像url
     */
    @TableField(value = "avatar")
    @ApiModelProperty(value = "头像url")
    private String avatar;

    /**
     * 0 男     1 女      2 未知
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "0 男     1 女      2 未知")
    private Integer sex;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    /**
     * 角色  0 - 普通用户 ， 1 - 管理员
     */
    @TableField(value = "role")
    @ApiModelProperty(value = "角色  0 - 普通用户 ， 1 - 管理员")
    private Integer role;

    /**
     * 学号
     */
    @TableField(value = "snumber")
    @ApiModelProperty(value = "学号")
    private String snumber;

    /**
     * 兴趣爱好，多个用逗号隔开
     */
    @TableField(value = "hobbies")
    @ApiModelProperty(value = "兴趣爱好，多个用逗号隔开")
    private String hobbies;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}