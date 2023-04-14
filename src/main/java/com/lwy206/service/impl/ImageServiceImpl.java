package com.lwy206.service.impl;

import com.lwy206.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final List<String> IMAGE_TYPE = List.of(
            "image/jpeg",
            "image/png",
            "image/jpg"
    );


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
    public byte[] rotateImage(String filePath, double degree) throws IOException, InterruptedException {
        // load python script from resources
        InputStream scriptStream = this.getClass().getClassLoader().getResourceAsStream("scripts/rotate_image.py");
        assert scriptStream != null;
        BufferedReader scriptReader = new BufferedReader(new InputStreamReader(scriptStream));
        StringBuilder scriptBuilder = new StringBuilder();
        String line;
        while ((line = scriptReader.readLine()) != null) {
            scriptBuilder.append(line).append("\n");
        }
        String scriptContent = scriptBuilder.toString();

        // write script content to temporary file
        File scriptFile = File.createTempFile("rotate_image", ".py");
        scriptFile.deleteOnExit();
        FileWriter writer = new FileWriter(scriptFile);
        writer.write(scriptContent);
        writer.close();

        // build command to execute python script
        List<String> command = List.of("python", scriptFile.getAbsolutePath(), filePath, String.valueOf(degree));

        // create process builder to run python script
        ProcessBuilder pb = new ProcessBuilder(command);
        Process process = pb.start();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException("Image rotation failed");
        }

        // read rotated image and convert to byte array
        return readFileToByteArray(filePath);
    }

    public byte[] readFileToByteArray(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readAllBytes(path);
    }
}
