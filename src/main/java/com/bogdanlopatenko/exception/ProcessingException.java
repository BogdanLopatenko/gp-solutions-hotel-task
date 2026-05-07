package com.bogdanlopatenko.exception;

public class ProcessingException extends RuntimeException {

    private String responseStatus;

    ProcessingException(String message, String responseStatus) {

        super(message);
        this.responseStatus = responseStatus;
    }

    public String getResponseStatus() {
        return responseStatus;
    }
}
