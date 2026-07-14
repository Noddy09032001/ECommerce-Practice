package com.code.ecommerce.exceptions;

// custom exception handler for exceptions occurring during item search
public class ItemSearchException extends RuntimeException{
    public ItemSearchException(String message){
        super(message);
    }
}
