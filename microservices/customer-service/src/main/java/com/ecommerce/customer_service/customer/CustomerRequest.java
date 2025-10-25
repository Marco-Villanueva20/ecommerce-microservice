package com.ecommerce.customer_service.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
    String id,
    
    @NotNull(message = "The first name is required")
    @NotBlank(message = "The first name cannot be blank")
    String firstName,
    
    @NotNull(message = "The last name is required")
    @NotBlank(message = "The last name cannot be blank")
    String lastName,

    @NotNull(message = "The email is required")
    @NotBlank(message = "The email cannot be blank")
    @Email(message = "The email must be a valid email address")
    String email,
    String phone,
    String address,
    String city
){}
