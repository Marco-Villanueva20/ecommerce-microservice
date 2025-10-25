package com.ecommerce.product_microservice.product;

import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        String description,
        @NotNull(message = "Product price is required")
        Double price,
        @NotNull(message = "Product stock is required")
        Integer stock,
        String imageUrl,
        @NotNull(message = "Product category is required")
        Integer categoryId
) {

}
