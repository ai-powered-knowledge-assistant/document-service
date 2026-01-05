package com.aiassistant.document.service;

import com.aiassistant.document.dto.DocumentUploadRequest;
import com.aiassistant.document.dto.DocumentUploadedEvent;
import com.aiassistant.document.entity.Document;
import com.aiassistant.document.entity.DocumentStatus;
import com.aiassistant.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final FileStorageService fileStorageService;
    private final DocumentEventPublisher eventPublisher;

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

    public Document uploadFile(MultipartFile file, String uploadedBy) {

        String storagePath = fileStorageService.store(file);

        Document document = Document.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .storagePath(storagePath)
                .status(DocumentStatus.UPLOADED)
                .uploadedBy(uploadedBy)
                .uploadedAt(LocalDateTime.now())
                .build();

        Document saved = documentRepository.save(document);

        DocumentUploadedEvent event = new DocumentUploadedEvent(
                saved.getId(),
                saved.getFileName(),
                saved.getFileType(),
                saved.getStoragePath()
        );

        eventPublisher.publish(event);

        return saved;

    }

}
