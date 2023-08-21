package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.StudentUpdateDto;
import com.group13.nsrs.model.entity.Student;
import com.group13.nsrs.service.StudentService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "更新学生信息", notes = "采集学生个人信息时使用")
    public Result<String> updateStudent(@RequestBody
                                        @ApiParam(value = "待更新学生信息", required = true)
                                        StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(studentUpdateDto);
    }

    @GetMapping
    @ApiOperation(value = "获取当前登录用户的学生信息", notes = "需要登录")
    public Result<Student> getStudentInfo() {
        return studentService.getStudentInfo();
    }
}
