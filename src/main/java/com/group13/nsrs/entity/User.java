package com.group13.nsrs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableId(value = "id")
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
    @TableField(value = "created_time")
    private LocalDateTime createdTime;

    /**
     * 角色  0 - 普通用户 ， 1 - 管理员
     */
    @TableField(value = "role")
    private Integer role;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}