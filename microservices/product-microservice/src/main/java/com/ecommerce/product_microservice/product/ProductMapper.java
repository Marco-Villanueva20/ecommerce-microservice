package com.ecommerce.product_microservice.product;

import org.springframework.stereotype.Service;

import com.ecommerce.product_microservice.category.Category;

@Service
public class ProductMapper {
    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getImageUrl(),
            product.getCategory().getId(),
            product.getCategory().getName(),
            product.getCategory().getDescription()
        );
    }


    public static Product toProduct(ProductRequest productRequest){
        return Product.builder()
        .name(productRequest.name())
        .description(productRequest.description())
        .price(productRequest.price())
        .stock(productRequest.stock())
        .imageUrl(productRequest.imageUrl())
        .category(
            Category.builder()
            .id(productRequest.categoryId())
            .build()
        )
        .build();
    }
}
