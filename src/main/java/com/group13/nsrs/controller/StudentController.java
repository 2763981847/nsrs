package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.StudentUpdateDto;
import com.group13.nsrs.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @PutMapping
    public Result<String> updateStudent(StudentUpdateDto studentUpdateDto) {
        return Result.ok();
    }
}
