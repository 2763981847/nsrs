package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.StudentUpdateDto;
import com.group13.nsrs.model.entity.Student;
import com.group13.nsrs.service.StudentService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */

@RestController
@RequestMapping("/api/student")
@Api(tags = "学生信息接口")
public class StudentController {
    @Resource
    private StudentService studentService;

    @PutMapping
    public Result<String> updateStudent(@RequestBody StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(studentUpdateDto);
    }

    @GetMapping
    public Result<Student> getStudentInfo() {
        return studentService.getStudentInfo();
    }
}
