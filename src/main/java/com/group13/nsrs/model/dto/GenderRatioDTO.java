package com.group13.nsrs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oreki
 */
@Data
@ApiModel(value = "总的性别比例", description = "已填学生信息收集表的学生性别比例")
@AllArgsConstructor
@NoArgsConstructor
public class GenderRatioDTO {
    //    @ApiModelProperty(value = "学院名称")
    //    private String college;
    @ApiModelProperty(value = "男生人数")
    private Integer male_count;
    @ApiModelProperty(value = "女生人数")
    private Integer female_count;
}
