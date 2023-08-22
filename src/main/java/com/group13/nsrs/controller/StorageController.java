package com.group13.nsrs.controller;

import com.group13.nsrs.service.StorageService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@RequestMapping("/api/file")
@RestController
@Api(tags = "文件服务接口")
public class StorageController {
    @Resource
    private StorageService storageService;

    @PostMapping("/picture")
    @ApiOperation(value = "上传图片", notes = "上传成功后会返回图片的url")
    public Result<String> uploadPicture(
            @RequestParam("file")
            @ApiParam(value = "待上传的图片", required = true)
            MultipartFile multipartFile) {
        return storageService.uploadPicture(multipartFile);
    }
}
