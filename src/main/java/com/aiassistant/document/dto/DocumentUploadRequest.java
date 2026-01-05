package com.aiassistant.document.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentUploadRequest {

    @NotBlank
    private String fileName;

    @NotBlank
    private String fileType;

    @NotNull
    private Long fileSize;

    @NotBlank
    private String storagePath;
}
