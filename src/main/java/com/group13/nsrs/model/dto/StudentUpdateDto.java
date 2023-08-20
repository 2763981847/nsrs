package com.group13.nsrs.model.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@Data
public class StudentUpdateDto {
    private Integer sex;
    private String phone;
    private String name;
    private String nation;
    private String certificatesNo;
    private LocalDate birth;
    private String province;
    private String city;
    private String district;
    private String address;
    private String contactName;
    private String contactPhone;
    private String contactRelationship;
}
