package com.group13.nsrs.service;

import com.group13.nsrs.model.entity.Dormitory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group13.nsrs.util.result.Result;

import java.util.List;

/**
* @author Oreki
* @description 针对表【dormitory】的数据库操作Service
* @createDate 2023-08-22 15:51:51
*/
public interface DormitoryService extends IService<Dormitory> {

    Result<List<Dormitory>> listDormitoriesByUser();
}
