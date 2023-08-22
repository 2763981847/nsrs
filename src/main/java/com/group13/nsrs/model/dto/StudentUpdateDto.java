package com.group13.nsrs.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@Data
@ApiModel(value = "新生信息收集", description = "收集的新生信息")
public class StudentUpdateDto {
    @ApiModelProperty(value = "性别 0 - 男 1 - 女 2 - 未知")
    private Integer sex;
    @ApiModelProperty(value = "电话号码")
    private String phone;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "学院id")
    private Long collegeId;
    @ApiModelProperty(value = "专业id")
    private Long majorId;
    @ApiModelProperty(value = "民族")
    private String nation;
    @ApiModelProperty(value = "身份证号")
    private String certificatesNo;
    @ApiModelProperty(value = "出生日期")
    private LocalDate birth;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "区")
    private String district;
    @ApiModelProperty(value = "详细信息")
    private String address;
    @ApiModelProperty(value = "联系人姓名")
    private String contactName;
    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;
    @ApiModelProperty(value = "联系人关系")
    private String contactRelationship;
    @ApiModelProperty(value = "是否已经报到 0 - 未报到 1 - 已报到")
    private Integer isReported;
}
