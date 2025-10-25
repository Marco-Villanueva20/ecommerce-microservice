package com.ecommerce.common_exception;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
    
    ){}
