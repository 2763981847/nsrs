package com.group13.nsrs.service;

import com.group13.nsrs.model.dto.StudentUpdateDto;
import com.group13.nsrs.model.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group13.nsrs.util.result.Result;

/**
 * @author Oreki
 * @description 针对表【student】的数据库操作Service
 * @createDate 2023-08-20 11:00:40
 */
public interface StudentService extends IService<Student> {

    Result<String> updateStudent(StudentUpdateDto studentUpdateDto);

    Result<Student> getStudentInfo();

}
