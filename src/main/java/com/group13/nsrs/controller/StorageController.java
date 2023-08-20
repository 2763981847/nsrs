package com.group13.nsrs.controller;

import com.group13.nsrs.service.StorageService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Result<String> uploadPicture(MultipartFile multipartFile) {
        return storageService.uploadPicture(multipartFile);
    }
}
