package com.aiassistant.document.controller;

import com.aiassistant.document.dto.DocumentUploadRequest;
import com.aiassistant.document.entity.Document;
import com.aiassistant.document.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Document uploadDocument(@RequestBody @Valid DocumentUploadRequest request , @RequestHeader("X-User-Id") String userId) {
        return documentService.upload(request, userId);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public Document uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("X-User-Id") String userId) {
        return documentService.uploadFile(file, userId);
    }
}
