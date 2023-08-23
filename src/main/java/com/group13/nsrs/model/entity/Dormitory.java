package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * 
 * @author Oreki
 * @TableName dormitory
 */
@TableName(value ="dormitory")
@Data
@ApiModel(value = "宿舍实体类")
public class Dormitory implements Serializable {
    /**
     * 宿舍id
     */
    @ApiModelProperty(value = "宿舍id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 宿舍号
     */
    @ApiModelProperty(value = "宿舍号")
    @TableField(value = "dormitoryno")
    private String dormitoryno;

    /**
     * 楼栋名
     */
    @TableField(value = "building")
    @ApiModelProperty(value = "楼栋名")
    private String building;

    /**
     * 学院id
     */
    @TableField(value = "college_id")
    @ApiModelProperty(value = "学院id")
    private Long collegeId;

    /**
     * 0 - 男生宿舍 1 - 女生宿舍
     */
    @ApiModelProperty(value = "0 - 男生宿舍 1 - 女生宿舍")
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 入住人数
     */
    @ApiModelProperty(value = "入住人数")
    @TableField(value = "count")
    private Integer count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}