package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName student
 */
@TableName(value = "student")
@Data
@ApiModel(value = "学生实体类")
public class Student implements Serializable {
    /**
     * 学生id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "学生id")
    private Long id;

    /**
     * 学号
     */
    @TableField(value = "snumber")
    @ApiModelProperty(value = "学号")
    private String snumber;

    /**
     * 学院
     */
    @TableField(value = "college_id")
    @ApiModelProperty(value = "学院id")
    private Long collegeId;

    /**
     * 专业
     */
    @TableField(value = "major_id")
    @ApiModelProperty(value = "专业id")
    private Long majorId;

    /**
     * 电话
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 姓名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 民族
     */
    @TableField(value = "nation")
    @ApiModelProperty(value = "民族")
    private String nation;

    /**
     * 身份证号
     */
    @TableField(value = "certificates_no")
    @ApiModelProperty(value = "身份证号")
    private String certificatesNo;

    /**
     * 出生日期
     */
    @TableField(value = "birth")
    @ApiModelProperty(value = "出生日期")
    private LocalDate birth;

    /**
     * 省
     */
    @TableField(value = "province")
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 市
     */
    @TableField(value = "city")
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 区
     */
    @TableField(value = "district")
    @ApiModelProperty(value = "区")
    private String district;

    /**
     * 详细地址
     */
    @TableField(value = "address")
    @ApiModelProperty(value = "详细地址")
    private String address;

    /**
     * 联系人姓名
     */
    @TableField(value = "contact_name")
    @ApiModelProperty(value = "联系人姓名")
    private String contactName;

    /**
     * 联系人电话
     */
    @TableField(value = "contact_phone")
    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;

    /**
     * 联系人关系
     */
    @TableField(value = "contact_relationship")
    @ApiModelProperty(value = "联系人关系")
    private String contactRelationship;

    /**
     * 性别 0:男 1:女 2:未知
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别 0:男 1:女 2:未知")
    private Integer sex;

    /**
     * -1 - 未开始，0-6 - 进行到了第几步，7 - 已报到
     */
    @TableField(value = "reported_status")
    @ApiModelProperty(value = "-1 - 未开始，0-6 - 进行到了第几步，7 - 已报到")
    private Integer reportedStatus;


    /**
     * 报到日期
     */
    @TableField(value = "report_date")
    @ApiModelProperty(value = "报到日期")
    private LocalDate reportDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}