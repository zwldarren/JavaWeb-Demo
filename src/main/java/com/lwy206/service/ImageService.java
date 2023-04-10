package com.lwy206.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    boolean isImage(MultipartFile file) throws IOException;

    byte[] readFileToByteArray(String filePath) throws IOException;

    byte[] rotateImage(String filePath, double degree) throws IOException, InterruptedException;
}
