package com.group13.nsrs.service.impl;

import com.group13.nsrs.service.FileStorageService;
import com.group13.nsrs.service.StorageService;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private FileStorageService fileStorageService;

    @Override
    public Result<String> uploadPicture(MultipartFile multipartFile) {
        //1.检查参数
        if (multipartFile == null || multipartFile.isEmpty()) {
            Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        //2.上传图片到minIO中
        // 生成文件名
        String filename = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = null;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            url = fileStorageService.uploadImgFile("", filename + suffix, inputStream);
        } catch (IOException e) {
            return Result.fail(ResultCodeEnum.SERVICE_ERROR);
        }
        return Result.ok(url);
    }
}
