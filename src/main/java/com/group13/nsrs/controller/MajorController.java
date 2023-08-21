package com.group13.nsrs.controller;

import com.group13.nsrs.model.entity.College;
import com.group13.nsrs.model.entity.Major;
import com.group13.nsrs.service.CollegeService;
import com.group13.nsrs.service.MajorService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@RestController
@Api(tags = "专业信息接口")
@RequestMapping("/api/major")
public class MajorController {
    @Resource
    private MajorService majorService;

    @GetMapping("/{id}")
    public Result<Major> getMajorInfo(@PathVariable Long id) {
        return Result.ok(majorService.getById(id));
    }

    @GetMapping
    public Result<List<Major>> listCollegeInfos() {
        return Result.ok(majorService.list());
    }
}
