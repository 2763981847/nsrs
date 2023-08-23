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
 * @TableName major
 */
@TableName(value ="major")
@Data
@ApiModel(value = "专业实体类")
public class Major implements Serializable {
    /**
     * 专业id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "专业id")
    private Long id;

    /**
     * 专业名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "专业名称")
    private String name;

    /**
     * 学院id
     */
    @TableField(value = "college_id")
    @ApiModelProperty(value = "学院id")
    private Long collegeId;

    /**
     * 学院名称
     */
    @TableField(value = "college_name")
    @ApiModelProperty(value = "学院名称")
    private String collegeName;

    /**
     * 专业详细信息
     */
    @TableField(value = "major_detail")
    @ApiModelProperty(value = "专业详细信息")
    private String majorDetail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}