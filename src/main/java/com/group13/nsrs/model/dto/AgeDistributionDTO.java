package com.group13.nsrs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "年龄分布",description = "已填学生信息收集表的学生的年龄分布")
@AllArgsConstructor
@NoArgsConstructor
public class AgeDistributionDTO {
    @ApiModelProperty(value = "年龄" ,required = true)
    private Integer age;
    @ApiModelProperty(value = "这个年龄段的学生人数",required = true)
    private Integer age_count;
}
