package com.group13.nsrs.service;

import com.group13.nsrs.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
public interface StorageService {
    Result<String> uploadPicture(MultipartFile multipartFile);
}
