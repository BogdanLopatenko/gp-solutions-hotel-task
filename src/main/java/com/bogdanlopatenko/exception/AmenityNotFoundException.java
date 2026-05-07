package com.bogdanlopatenko.exception;

public class AmenityNotFoundException extends ProcessingException{
    public AmenityNotFoundException(String message, String responseStatus) {
        super(message, responseStatus);
    }
}
