package com.bogdanlopatenko.exception.handler;

import com.bogdanlopatenko.dto.ExceptionResponseDto;
import com.bogdanlopatenko.exception.BrandNotFoundException;
import com.bogdanlopatenko.exception.HotelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleDocumentNotFoundException(BrandNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(exception.getResponseStatus(), exception.getMessage()));
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleDocumentStatusConflictException(HotelNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(exception.getResponseStatus(), exception.getMessage()));
    }
}
