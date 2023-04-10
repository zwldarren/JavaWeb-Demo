package com.lwy206.controller;

import com.lwy206.entity.response.Result;
import com.lwy206.service.ImageService;
import com.lwy206.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Upload Controller
 */
@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private UploadService fileUploadService;
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public Result<byte[]> upload(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        // check if file is image
        if (!imageService.isImage(file)) {
            return Result.error(415, "File is not image");
        }

        // save file to disk
        Path savedFilePath = fileUploadService.upload(file);

        // rotate image
        imageService.rotateImage(savedFilePath.toString(), 90);

        // return byte[] of image
        return Result.success(file.getBytes());
    }

    @GetMapping("/test")
    public <T> Result<T> test(@RequestParam("str") T str) {
        return Result.success(str);
    }


}
