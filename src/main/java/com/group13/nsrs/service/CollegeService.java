package com.group13.nsrs.service;

import com.group13.nsrs.model.entity.College;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group13.nsrs.model.vo.MultiLevelSelection;
import com.group13.nsrs.util.result.Result;

import java.util.List;

/**
* @author Oreki
* @description 针对表【college】的数据库操作Service
* @createDate 2023-08-20 11:00:40
*/
public interface CollegeService extends IService<College> {


    Result<List<MultiLevelSelection>> listCollegesWithMajors();

}
