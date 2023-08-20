package com.group13.nsrs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "各学院性别比例",description = "各学院性别比例")
@AllArgsConstructor
@NoArgsConstructor
public class genderRatioDTO {
    @ApiModelProperty(value = "学院名称")
    private String college;
    @ApiModelProperty(value = "男生人数")
    private Integer male_count;
    @ApiModelProperty(value = "女生人数")
    private Integer female_count;
}
