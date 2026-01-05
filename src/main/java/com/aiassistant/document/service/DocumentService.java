package com.aiassistant.document.service;

import com.aiassistant.document.dto.DocumentUploadRequest;
import com.aiassistant.document.entity.Document;
import com.aiassistant.document.entity.DocumentStatus;
import com.aiassistant.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public Document upload(DocumentUploadRequest request, String uploadedBy) {

        Document document = Document.builder()
                .fileName(request.getFileName())
                .fileType(request.getFileType())
                .fileSize(request.getFileSize())
                .storagePath(request.getStoragePath())
                .status(DocumentStatus.UPLOADED)
                .uploadedBy(uploadedBy)
                .uploadedAt(LocalDateTime.now())
                .build();

        return documentRepository.save(document);
    }
}
