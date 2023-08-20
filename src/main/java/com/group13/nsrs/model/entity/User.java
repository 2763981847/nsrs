package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 密码、通信等加密盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 用户名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 密码,md5加密
     */
    @TableField(value = "password")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 头像url
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 0 男     1 女      2 未知
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 创建时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 角色  0 - 普通用户 ， 1 - 管理员
     */
    @TableField(value = "role")
    private Integer role;

    /**
     * 学号
     */
    @TableField(value = "snumber")
    private String snumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}