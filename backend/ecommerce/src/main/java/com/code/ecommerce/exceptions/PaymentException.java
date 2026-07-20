package com.code.ecommerce.exceptions;

// exception class for issues during payment initiation using stripe
public class PaymentException extends RuntimeException{
    public PaymentException(String message){
        super(message);  // passing it to the runtime exception class
    }
}
