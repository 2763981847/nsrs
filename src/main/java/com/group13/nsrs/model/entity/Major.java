package com.group13.nsrs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName major
 */
@TableName(value ="major")
@Data
public class Major implements Serializable {
    /**
     * 专业id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 专业名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 学院id
     */
    @TableField(value = "college_id")
    private Long collegeId;

    /**
     * 学院名称
     */
    @TableField(value = "college_name")
    private String collegeName;

    /**
     * 专业详细信息
     */
    @TableField(value = "major_detail")
    private String majorDetail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}