package com.jjy.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/28
 * @time : 14:12
 */
public interface FileUploadService {
    /**
     * 获取上传文件的minio路径
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
