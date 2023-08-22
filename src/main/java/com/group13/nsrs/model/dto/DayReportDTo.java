package com.group13.nsrs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "每天报道的人数", description = "存储每天报道的人数")
public class DayReportDTo {
    @ApiModelProperty(value = "日期", required = true)
    private String report_date;
    @ApiModelProperty(value = "人数", required = true)
    private Integer count;

}
