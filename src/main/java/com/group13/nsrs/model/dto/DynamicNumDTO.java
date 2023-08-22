package com.group13.nsrs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "已报到人数", description = "统计已经报道的学生人数")
public class DynamicNumDTO {
    @ApiModelProperty(value = "已经报道的学生人数", required = true)
    private Integer dynamic_num;
}
