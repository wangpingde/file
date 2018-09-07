package com.hoperun.qkl.fileserve.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfig {

    public static String path;

    @Value("${file.serve}")
    public void setPath(String path) {
        UploadConfig.path = path;
    }
}
