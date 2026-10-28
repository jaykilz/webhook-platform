package dev.jaykilz.webhook_platform.controller;

import dev.jaykilz.webhook_platform.dto.PaymentWebhookRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    @PostMapping("/payments")
    public String receivePaymentWebhook(@RequestBody PaymentWebhookRequest request) {

        System.out.println("Event ID: " + request.getEventId());
        System.out.println("Order ID: " + request.getOrderId());
        System.out.println("Status: " + request.getStatus());
        System.out.println("Amount: " + request.getAmount());

        return "Webhook received successfully!";
    }
}
