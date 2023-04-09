package com.lwy206.service.impl;

import com.lwy206.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final List<String> IMAGE_TYPE = List.of(
            "image/jpeg",
            "image/png",
            "image/jpg"
    );

    @Autowired
    private Environment env;

    @Override
    public boolean isImage(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (contentType != null && IMAGE_TYPE.contains(contentType.toLowerCase())) {
            BufferedImage image = ImageIO.read(file.getInputStream());
            return image != null;
        }
        return false;
    }

    @Override
    public MultipartFile rotateImage(String fileName, String degree) throws FileNotFoundException {

        return null;
    }
}
