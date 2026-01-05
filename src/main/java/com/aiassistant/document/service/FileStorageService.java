package com.aiassistant.document.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.storage.location}")
    private String storageLocation;

    public String store(MultipartFile file) {

        try {
            Files.createDirectories(Paths.get(storageLocation));

            String storageFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            Path targetPath = Paths.get(storageLocation).resolve(storageFileName).normalize();

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return targetPath.toString();

        }catch (Exception e){
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
