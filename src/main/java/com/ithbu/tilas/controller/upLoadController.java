package com.ithbu.tilas.controller;

import com.aliyuncs.exceptions.ClientException;
import com.ithbu.tilas.pojo.Result;
import com.ithbu.tilas.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class upLoadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
//    @PostMapping("/upload")
//    public Result upload(MultipartFile image) throws Exception {
//        log.info("文件上传,{},{},{}",image);
//        String originalFilename = image.getOriginalFilename();
//        int i = originalFilename.lastIndexOf(".");
//        String newFileName = UUID.randomUUID().toString()+originalFilename.substring(i);
//        image.transferTo(new File("D:\\image\\"+newFileName));
//        return Result.success();
//    }
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("上传图片");
        String upload = aliOSSUtils.upload(image);
        return Result.success(upload);
    }
}
