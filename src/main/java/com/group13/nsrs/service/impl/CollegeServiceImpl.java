package com.group13.nsrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.entity.College;
import com.group13.nsrs.service.CollegeService;
import com.group13.nsrs.mapper.CollegeMapper;
import org.springframework.stereotype.Service;

/**
* @author Oreki
* @description 针对表【college】的数据库操作Service实现
* @createDate 2023-08-20 11:00:40
*/
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College>
    implements CollegeService{

}




