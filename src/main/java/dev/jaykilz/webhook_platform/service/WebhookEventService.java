package dev.jaykilz.webhook_platform.service;

import dev.jaykilz.webhook_platform.dto.PaymentWebhookRequest;
import dev.jaykilz.webhook_platform.entity.WebhookEvent;
import dev.jaykilz.webhook_platform.repository.WebhookEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WebhookEventService {

    private final WebhookEventRepository repository;

    public WebhookEventService(WebhookEventRepository repository) {
        this.repository = repository;
    }

    public String receiveEvent(PaymentWebhookRequest request) {

        // Check if event already exists
        if (repository.existsByEventId(request.getEventId())) {
            return "Webhook already received!";
        }

        // Create new event
        WebhookEvent event = new WebhookEvent();

        event.setEventId(request.getEventId());
        event.setOrderId(request.getOrderId());
        event.setStatus(request.getStatus());
        event.setAmount(request.getAmount());
        event.setReceivedAt(LocalDateTime.now());
        event.setProcessingStatus("RECEIVED");

        // Save event
        repository.save(event);

        return "Webhook received and saved!";
    }

    public String processEvent(String eventId) {

        // Find event in database
        WebhookEvent event = repository.findByEventId(eventId)
                .orElse(null);

        // Check if event exists
        if (event == null) {
            return "Webhook event not found!";
        }

        // Update status to PROCESSING
        event.setProcessingStatus("PROCESSING");
        repository.save(event);

        // Simulate processing the event
        System.out.println("Processing event: " + eventId);

        // Update status to PROCESSED
        event.setProcessingStatus("PROCESSED");
        repository.save(event);

        return "Webhook processed successfully!";
    }
}

