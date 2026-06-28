package com.code.ecommerce.exceptions;

// exception class for the invalid item found
public class InvalidItemException extends RuntimeException{
    public InvalidItemException(String message){
        super(message);
    }
}
