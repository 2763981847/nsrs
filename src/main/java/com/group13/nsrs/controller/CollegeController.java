package com.group13.nsrs.controller;

import com.group13.nsrs.model.entity.College;
import com.group13.nsrs.service.CollegeService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@RestController
@CrossOrigin //用于处理用户跨域问题
@RequestMapping("/api/college")
@Api(tags = "学院信息接口")
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    @GetMapping("/{id}")
    public Result<College> getCollegeInfo(@PathVariable Long id) {
        return Result.ok(collegeService.getById(id));
    }

    @GetMapping
    public Result<List<College>> listCollegeInfos() {
        return Result.ok(collegeService.list());
    }


}
