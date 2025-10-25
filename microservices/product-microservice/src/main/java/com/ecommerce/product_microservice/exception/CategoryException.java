package com.ecommerce.product_microservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryException extends RuntimeException{
    private final String msg;
} 
