package com.code.ecommerce.exceptions;

// custom exception handler for errors occurred during order search
public class OrderSearchException extends RuntimeException{
    public OrderSearchException(String message){
        super(message);
    }
}
