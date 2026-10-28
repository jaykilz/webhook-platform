package dev.jaykilz.webhook_platform.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    @PostMapping("/payments")
    public String receivePaymentWebhook() {
        return "Webhook received successfully!";
    }
}
