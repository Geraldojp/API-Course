package com.example.belajar_spring.controller;

import com.example.belajar_spring.exception.NotFoundException;
import com.example.belajar_spring.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAllException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("X06", e.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFound(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ErrorResponse("X01", e.getMessage()));
    }
}
