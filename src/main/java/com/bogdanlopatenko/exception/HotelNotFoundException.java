package com.bogdanlopatenko.exception;

public class HotelNotFoundException extends ProcessingException {
    public HotelNotFoundException(String message, String responseStatus) {
        super(message, responseStatus);
    }
}
