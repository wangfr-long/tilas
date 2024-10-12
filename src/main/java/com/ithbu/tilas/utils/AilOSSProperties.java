package com.ithbu.tilas.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AilOSSProperties {
    private String bucketName;
    private String endpoint ;
}
