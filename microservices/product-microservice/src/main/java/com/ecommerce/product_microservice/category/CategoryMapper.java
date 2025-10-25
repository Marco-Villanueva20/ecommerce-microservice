package com.ecommerce.product_microservice.category;

import org.springframework.stereotype.Service;

import com.ecommerce.product_microservice.product.ProductMapper;

@Service
public class CategoryMapper {

    public Category toCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription(),
            category.getProducts().stream().map(ProductMapper::toProductResponse).toList()
        );
    }

}
