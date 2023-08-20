package com.group13.nsrs.controller;

import com.group13.nsrs.model.entity.College;
import com.group13.nsrs.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@RestController
@RequestMapping("/api/college")
public class CollegeController {
    @GetMapping("/{id}")
    public Result<College> getCollegeInfo(@PathVariable Long id) {
        return Result.ok();
    }

    @GetMapping
    public Result<List<College>> listCollegeInfos() {
        return Result.ok();
    }
}
