package com.group13.nsrs.controller;

import com.group13.nsrs.model.entity.Dormitory;
import com.group13.nsrs.service.DormitoryService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/22
 */
@RequestMapping("/api/dormitory")
@RestController
@Api(tags = "宿舍信息接口")
public class DormitoryController {
    @Resource
    private DormitoryService dormitoryService;

    @GetMapping
    @ApiOperation("获取所有宿舍信息")
    public Result<List<Dormitory>> listDormitories() {
        return Result.ok(dormitoryService.list());
    }

    @GetMapping("/user")
    @ApiOperation("根据当前登录用户获取对应宿舍信息")
    public Result<List<Dormitory>> listDormitoriesByUser() {
        return dormitoryService.listDormitoriesByUser();
    }
}
