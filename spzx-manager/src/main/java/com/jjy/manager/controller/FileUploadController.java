package com.jjy.manager.controller;

import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.manager.service.impl.FileUploadServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/28
 * @time : 14:10
 */
@RestController
@RequestMapping("/admin/system/")
public class FileUploadController {

    @Resource
    private FileUploadServiceImpl fileUploadService;

    @PostMapping("/fileUpload")
    public Result fileUpload(@RequestBody MultipartFile file) {
        //获取上传文件
        //调用service方法上传，返回minio路径
        String url = fileUploadService.upload(file);
        System.out.println("url = " + url);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
