package dev.jaykilz.webhook_platform.controller;

import dev.jaykilz.webhook_platform.dto.PaymentWebhookRequest;
import dev.jaykilz.webhook_platform.entity.WebhookEvent;
import dev.jaykilz.webhook_platform.repository.WebhookEventRepository;
import dev.jaykilz.webhook_platform.service.WebhookEventService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    private final WebhookEventService service;

    public WebhookController(WebhookEventService service) {
        this.service = service;
    }

    @PostMapping("/payments")
    public String receivePaymentWebhook(@RequestBody PaymentWebhookRequest request) {

        return service.receiveEvent(request);
    }

    @PostMapping("/{eventId}/process")
    public String processEvent(@PathVariable String eventId) {
        return service.processEvent(eventId);
    }
}
