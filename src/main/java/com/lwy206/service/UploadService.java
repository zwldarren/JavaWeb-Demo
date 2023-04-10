package com.lwy206.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;


public interface UploadService {

        Path upload(MultipartFile file) throws IOException;

}
