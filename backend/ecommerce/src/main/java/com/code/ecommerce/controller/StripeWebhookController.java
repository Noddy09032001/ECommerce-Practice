package com.code.ecommerce.controller;

import com.code.ecommerce.service.orders.OrderServiceImplementation;
import com.code.ecommerce.service.orders.PaymentService;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/webhooks")
public class StripeWebhookController {

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    private final PaymentService paymentService;
    private static Logger logger = LoggerFactory.getLogger(StripeWebhookController.class);

    @Autowired
    public StripeWebhookController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/stripe")
    public ResponseEntity<?> generateStripeEvents(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws Exception{
        Event event = null;
        try{
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret); // building the event
            paymentService.generateStripeEvents(event);  // calling the method to handle webhooks data and response
            return ResponseEntity.ok("Events received successfully");
        } catch (RuntimeException e) {
            logger.info("Error during generating stripe events: {}",e.getMessage());
            return ResponseEntity.badRequest().build();  // returning in case of failures
        }
    }
}
