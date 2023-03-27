package com.example.belajar_spring.controller;

import com.example.belajar_spring.exception.EmptyInputException;
import com.example.belajar_spring.exception.ExistedDataException;
import com.example.belajar_spring.exception.NotFoundException;
import com.example.belajar_spring.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotValid(MethodArgumentNotValidException e){
        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();
        for(FieldError error: fieldErrorList){
            errors.add(error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("X02", errors.toString()));
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleExistedData(ExistedDataException e){
        return ResponseEntity.status((HttpStatus.BAD_REQUEST))
                .body(new ErrorResponse("X04", e.getMessage()));
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEmptyInput(EmptyInputException e){
        return ResponseEntity.status((HttpStatus.BAD_REQUEST))
                .body(new ErrorResponse("X02", e.getMessage()));
    }
}
