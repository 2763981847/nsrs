package com.group13.nsrs.config;


import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author Oreki
 */
@Data
@ConfigurationProperties(prefix = "minio")  // 文件上传 配置前缀file.oss
@ConditionalOnProperty(prefix = "minio", name = "endpoint")
public class MinIOConfigProperties implements Serializable {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String endpoint;
    private String readPath;
}
