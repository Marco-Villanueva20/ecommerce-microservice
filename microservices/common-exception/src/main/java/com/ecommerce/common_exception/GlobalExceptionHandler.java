package com.ecommerce.common_exception;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.MethodArgumentNotValidException;;

@Component
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();
        
        exception.getBindingResult().getFieldErrors().forEach(error->{
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.warn("Validation errors: {}", exception.toString());
        return ResponseEntity.badRequest().body(new ErrorResponse(errors));
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        var error = new HashMap<String, String>();
        var fieldName = "message";
        var erroMessage = "Se ha producido un error. Por favor, contacte al administrador o intente más tarde";
        error.put(fieldName, erroMessage);
        log.error("Error: {}", exception.toString());
        return ResponseEntity.internalServerError().body(new ErrorResponse(error));  
    }





}
