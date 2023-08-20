package com.group13.nsrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.entity.Student;
import com.group13.nsrs.service.StudentService;
import com.group13.nsrs.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author Oreki
* @description 针对表【student】的数据库操作Service实现
* @createDate 2023-08-20 10:49:28
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




