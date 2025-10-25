package com.ecommerce.product_microservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductException extends RuntimeException {
    private final String message;
}

