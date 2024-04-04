package com.lina.controller;

import com.lina.pojo.Result;
import com.lina.service.FirebaseStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    // upload in firebase cloud:
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile image) throws IOException {
        log.info("upload into firebase cloud:{}.",image);
        // Delegate to the service to handle the file upload
        String url = firebaseStorageService.uploadFile(image);
        log.info("uploaded. url:{} ", url);
        return Result.success(url);
    }
    // upload in local storage. we don't use it now:
    @PostMapping("/upload-local")
    public Result uploadLocal(String username, Integer age, MultipartFile image) throws IOException {
        log.info("upload file {},{},{}.",username, age, image);
        // get file name
        String originalFilename = image.getOriginalFilename();
        // uuid: uniform unique id as name
        int index = originalFilename.lastIndexOf(".");
        // get file type(suffix)
        String extname = originalFilename.substring(index);
        // generate new name
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("new name: {}",newFileName);

        // local storage
        image.transferTo(new File("/Users/wulin/Desktop/WholeJourney/Java_web/springboot_dbms_project/src/main/resources/static/" + newFileName));
        return Result.success();
    }
}
