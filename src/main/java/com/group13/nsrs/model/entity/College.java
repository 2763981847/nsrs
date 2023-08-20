package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @author Oreki
 * @TableName college
 */
@TableName(value ="college")
@Data
public class College implements Serializable {
    /**
     * 学院id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学院名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 院长姓名
     */
    @TableField(value = "dean_name")
    private String deanName;

    /**
     * 学院所在地
     */
    @TableField(value = "location")
    private String location;

    /**
     * 学院网址
     */
    @TableField(value = "website")
    private String website;

    /**
     * 学院详细信息
     */
    @TableField(value = "college_detail")
    private String collegeDetail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}