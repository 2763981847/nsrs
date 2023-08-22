package com.group13.nsrs.controller;

import com.group13.nsrs.model.entity.College;
import com.group13.nsrs.model.vo.MultiLevelSelection;
import com.group13.nsrs.service.CollegeService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation("获取指定学院信息")
    public Result<College> getCollegeInfo(@PathVariable
                                          @ApiParam(value = "学院id", required = true)
                                          Long id) {
        return Result.ok(collegeService.getById(id));
    }

    @GetMapping
    @ApiOperation("获取所有学院信息")
    public Result<List<College>> listCollegeInfos() {
        return Result.ok(collegeService.list());
    }

    @GetMapping("/CollegesWithMajors")
    @ApiOperation("获取所有学院信息及其下面的专业信息")
    public Result<List<MultiLevelSelection>> listCollegesWithMajors() {
        return collegeService.listCollegesWithMajors();
    }


}
