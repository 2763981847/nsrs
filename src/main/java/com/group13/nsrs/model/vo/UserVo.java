package com.group13.nsrs.model.vo;

import lombok.Data;

import javax.annotation.Resource;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@Data
public class UserVo {
    private Long id;


    private String name;

    private String phone;

    private String avatar;
    private Integer sex;

    private String snumber;

    private Integer role;
}
