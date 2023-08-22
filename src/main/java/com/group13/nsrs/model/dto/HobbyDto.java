package com.group13.nsrs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "兴趣爱好分布",description = "兴趣爱好分布情况")
@AllArgsConstructor
@NoArgsConstructor
public class HobbyDto {
    @ApiModelProperty(value = "兴趣", required = true)
    private String hobby;
    @ApiModelProperty(value = "选择该兴趣的人数", required = true)
    private String hobby_count;
}
