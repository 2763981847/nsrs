package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.StudentUpdateDto;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */

@RestController
@RequestMapping("/api/student")
@Api(tags = "学生信息接口")
public class StudentController {
    @PutMapping
    public Result<String> updateStudent(@RequestBody StudentUpdateDto studentUpdateDto) {
        return Result.ok();
    }
}
