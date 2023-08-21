package com.group13.nsrs.service;

import com.group13.nsrs.model.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group13.nsrs.util.result.Result;

import java.util.List;

/**
* @author Oreki
* @description 针对表【major】的数据库操作Service
* @createDate 2023-08-20 11:00:40
*/
public interface MajorService extends IService<Major> {

    Result<List<Major>> listMajorsByCollegeId(Long collegeId);
}
