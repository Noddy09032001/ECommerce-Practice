package com.code.ecommerce.common.constants;

public enum PaymentStatus {
    PENDING("Payment is pending."),           // payment is awaiting completion
    SUCCESS("Payment completed successfully."), // payment completed successfully
    FAILED("Payment failed."),                // payment could not be processed
    CANCELLED("Payment was cancelled."),      // payment was canceled before completion
    REFUNDED("Payment has been refunded.");   // payment amount has been refunded

    private final String message;   // getting the message from the constant

    PaymentStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
