package com.group13.nsrs.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Fu Qiujie
 * @since 2023/8/22
 */
@ApiModel("宿舍需求保存数据传输对象")
@Data
public class DormiRequireDto {

    private String qq;

    private String phone;

    private String name;

    private String content;

}
