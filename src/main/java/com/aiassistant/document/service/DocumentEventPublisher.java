package com.aiassistant.document.service;

import com.aiassistant.document.dto.DocumentUploadedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentEventPublisher {

    private static final String TOPIC = "document.uploaded";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(DocumentUploadedEvent event) {
        kafkaTemplate.send(TOPIC, event.getDocumentId().toString(), event);
    }
}
