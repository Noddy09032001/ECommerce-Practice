package com.code.ecommerce.controller;

import com.code.ecommerce.common.constants.RequestTracker;
import com.code.ecommerce.dto.requests.payments.PaymentRequest;
import com.code.ecommerce.dto.response.ApiResponse;
import com.code.ecommerce.service.orders.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequest request, @RequestHeader("idempotency-key") String idempotencyKey) throws Exception {
        if(!RequestTracker.addPaymentKeys(idempotencyKey)) // checking for the order key
            throw new RuntimeException("Request with the same identifier key identified. Duplicate request");
        String stripeUrl = paymentService.initiatePayment(request);  // generating the payment url
        ApiResponse response = new ApiResponse();
        response.setMessage("Payment link has been created successfully");  // setting the message on success
        response.setData(stripeUrl);  // setting the payment url
        response.setStatusCode(HttpStatus.CREATED);  // setting the success status code
        return ResponseEntity.ok(response);  // returning the response body
    }
}
