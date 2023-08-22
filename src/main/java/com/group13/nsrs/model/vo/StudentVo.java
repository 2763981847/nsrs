package com.group13.nsrs.model.vo;

import com.group13.nsrs.model.entity.Student;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("学生信息数据视图对象")
@Data
public class StudentVo extends Student {
    @ApiModelProperty("学院名称")
    private String collegeName;
    @ApiModelProperty("专业名称")
    private String majorName;
}
