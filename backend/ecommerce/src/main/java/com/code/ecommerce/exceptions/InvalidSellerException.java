package com.code.ecommerce.exceptions;

// custom exception class for the seller merchant checks
public class InvalidSellerException extends RuntimeException{
    public InvalidSellerException(String message){
        super(message);
    }
}
