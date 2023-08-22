package com.group13.nsrs.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@Data
@ApiModel(value = "学院信息及其下专业信息数据视图对象")
public class MultiLevelSelection {
    private Long value;
    private String label;
    private Integer extra;
    private List<MultiLevelSelection> children;
}
