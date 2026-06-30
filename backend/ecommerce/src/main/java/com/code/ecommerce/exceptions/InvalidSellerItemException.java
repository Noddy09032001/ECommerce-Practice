package com.code.ecommerce.exceptions;

// custom exception handler for the handling of fetching seller item mapping
public class InvalidSellerItemException extends RuntimeException{
    public InvalidSellerItemException(String message){
        super(message);
    }
}
