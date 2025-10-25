package com.ecommerce.product_microservice.exception;

import java.util.HashMap;

import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.common_exception.ErrorResponse;
import com.ecommerce.common_exception.GlobalExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(basePackages="com.ecommerce.product_microservice")
@Primary
@Slf4j
public class CategoryExceptionHandler extends GlobalExceptionHandler{
    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ErrorResponse> handle(CategoryException exception){
        var error = new HashMap<String, String>();
        var fieldName = "category";
        var erroMessage = exception.getMsg();
        error.put(fieldName, erroMessage);
        log.warn("Category not found: {}", exception.toString());
        return ResponseEntity.badRequest().body(new ErrorResponse(error));
    }

}
