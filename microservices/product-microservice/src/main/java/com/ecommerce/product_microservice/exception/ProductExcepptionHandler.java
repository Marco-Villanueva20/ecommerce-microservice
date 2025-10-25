package com.ecommerce.product_microservice.exception;

import java.util.HashMap;

import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.common_exception.ErrorResponse;
import com.ecommerce.common_exception.GlobalExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(basePackages = "com.ecommerce.customer_service")
@Primary
@Slf4j
public class ProductExcepptionHandler extends GlobalExceptionHandler{

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorResponse> handle(ProductException exception){
        var error = new HashMap<String, String>();
        var fieldName = "product";
        var erroMessage = exception.getMessage();
        error.put(fieldName, erroMessage);
        log.warn("Product not found: {}", exception.toString());
        return ResponseEntity.badRequest().body(new ErrorResponse(error));
    }

}
