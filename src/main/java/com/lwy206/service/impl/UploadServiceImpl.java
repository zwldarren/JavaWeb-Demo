package com.lwy206.service.impl;

import com.lwy206.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    private final Environment env;

    @Autowired
    public UploadServiceImpl(Environment env) {
        this.env = env;
    }

    @Override
    public Path upload(MultipartFile file) throws IOException {

        // get file name and suffix
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // convert MultipartFile to byte[]
        byte[] bytes = file.getBytes();

        // generate a new file name using UUID
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + suffixName;

        // save file to disk
        String savePath = env.getProperty("file.upload.path");
        assert savePath != null;
        Path path = Path.of(savePath, newFileName);
        Files.write(path, bytes);

        return path;
    }



}
