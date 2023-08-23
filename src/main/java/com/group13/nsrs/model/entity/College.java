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
 * @TableName college
 */
@TableName(value ="college")
@Data
@ApiModel(value = "学院实体类")
public class College implements Serializable {
    /**
     * 学院id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "学院id")
    private Long id;

    /**
     * 学院名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "学院名称")
    private String name;

    /**
     * 院长姓名
     */
    @TableField(value = "dean_name")
    @ApiModelProperty(value = "院长姓名")
    private String deanName;

    /**
     * 学院所在地
     */
    @TableField(value = "location")
    @ApiModelProperty(value = "学院所在地")
    private String location;

    /**
     * 学院网址
     */
    @TableField(value = "website")
    @ApiModelProperty(value = "学院网址")
    private String website;

    /**
     * 学院详细信息
     */
    @TableField(value = "college_detail")
    @ApiModelProperty(value = "学院详细信息")
    private String collegeDetail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}