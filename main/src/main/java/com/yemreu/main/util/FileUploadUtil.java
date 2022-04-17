package com.yemreu.main.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void saveFile(Path uploadDirection, String fileName, MultipartFile multipartFile) throws IOException {
        if (!Files.exists(uploadDirection)) {
            Files.createDirectories(uploadDirection);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDirection.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException io) {
            throw new IOException("Could not save image file: " + fileName + io);
        }
    }
}
