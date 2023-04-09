package com.lwy206.service;

import com.lwy206.entity.response.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImageService {

    public boolean isImage(MultipartFile file) throws IOException;

    public MultipartFile rotateImage(String uuidFileName, String degree) throws FileNotFoundException;


}
