package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    /**
     * 学生id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学号
     */
    @TableField(value = "snumber")
    private String snumber;

    /**
     * 学院
     */
    @TableField(value = "college_id")
    private Long collegeId;

    /**
     * 专业
     */
    @TableField(value = "major_id")
    private Long majorId;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 民族
     */
    @TableField(value = "nation")
    private String nation;

    /**
     * 身份证号
     */
    @TableField(value = "certificates_no")
    private String certificatesNo;

    /**
     * 出生日期
     */
    @TableField(value = "birth")
    private LocalDate birth;

    /**
     * 省
     */
    @TableField(value = "province")
    private String province;

    /**
     * 市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 区
     */
    @TableField(value = "district")
    private String district;

    /**
     * 详细地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 联系人姓名
     */
    @TableField(value = "contact_name")
    private String contactName;

    /**
     * 联系人电话
     */
    @TableField(value = "contact_phone")
    private String contactPhone;

    /**
     * 联系人关系
     */
    @TableField(value = "contact_relationship")
    private String contactRelationship;

    /**
     * 性别 0:男 1:女 2:未知
     */
    @TableField(value = "sex")
    private Integer sex;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}