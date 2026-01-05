package com.aiassistant.document.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class DocumentUploadedEvent {

    private UUID documentId;
    private String fileName;
    private String fileType;
    private String storagePath;

    // REQUIRED for Jackson
    public DocumentUploadedEvent() {
    }

    public DocumentUploadedEvent(UUID documentId, String fileName,
                                 String fileType, String storagePath) {
        this.documentId = documentId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.storagePath = storagePath;
    }

}
