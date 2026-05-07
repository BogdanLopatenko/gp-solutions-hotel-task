package com.bogdanlopatenko.exception;

public class BrandNotFoundException extends ProcessingException {
    public BrandNotFoundException(String message, String responseStatus) {
        super(message, responseStatus);
    }
}
