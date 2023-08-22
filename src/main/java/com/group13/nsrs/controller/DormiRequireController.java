package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.DormiRequireDto;
import com.group13.nsrs.model.entity.DormiRequirement;
import com.group13.nsrs.service.DormiRequirementService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/22
 */
@RequestMapping("/api/v1/dormi_require")
@RestController
@Api(tags = "宿舍需求接口")
public class DormiRequireController {
    @Resource
    private DormiRequirementService dormiRequirementService;

    @PostMapping
    @ApiOperation("保存宿舍需求")
    public Result<String> save(@RequestBody DormiRequireDto dormiRequireDto) {
        return dormiRequirementService.saveDormiRequire(dormiRequireDto);
    }


    @GetMapping("/{dormitoryId}")
    @ApiOperation("获取指定宿舍已入住人员的宿舍需求")
    public Result<List<DormiRequirement>> listByDormitory(@PathVariable Long dormitoryId) {
        return dormiRequirementService.listByDormitory(dormitoryId);
    }
}
