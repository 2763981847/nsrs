package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author Oreki
 * @TableName dormi_requirement
 */
@TableName(value ="dormi_requirement")
@Data
@ApiModel(value = "宿舍需求实体类")
public class DormiRequirement implements Serializable {
    /**
     * 宿舍需求id
     */
    @ApiModelProperty(value = "宿舍需求id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学号
     */
    @ApiModelProperty(value = "学号")
    @TableField(value = "snumber")
    private String snumber;

    /**
     * qq号
     */
    @ApiModelProperty(value = "qq号")
    @TableField(value = "qq")
    private String qq;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    @TableField(value = "phone")
    private String phone;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @TableField(value = "name")
    private String name;

    /**
     * 需求内容
     */
    @ApiModelProperty(value = "需求内容")
    @TableField(value = "content")
    private String content;

    /**
     * 已入住宿舍号
     */
    @ApiModelProperty(value = "已入住宿舍号")
    @TableField(value = "dormitory_id")
    private Long dormitoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}