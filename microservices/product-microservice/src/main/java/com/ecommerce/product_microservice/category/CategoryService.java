package com.ecommerce.product_microservice.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.product_microservice.exception.CategoryException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> {
                    return categoryMapper.toCategoryResponse(category);
                })
                .toList();
    }

    public Integer createCategory(CategoryRequest categoryRequest) {
        Category category = categoryRepository.save(categoryMapper.toCategory(categoryRequest));
        return category.getId();
    }

    public void deleteCategoryById(Integer id) {
        if (id == null) {
            throw new CategoryException("Category id cannot be null or blank");
        }else if (!categoryRepository.existsById(id)) {
            throw new CategoryException(String.format("Category with id %s not found", id));
        }
        categoryRepository.deleteById(id);
    }

    public Integer updateCategory(CategoryRequest categoryRequest) {
        if (categoryRequest == null || categoryRequest.id() ==null ) {
            throw new CategoryException("Category ID cannot be null");
        }else if (!categoryRepository.existsById(categoryRequest.id())){
            throw new CategoryException("Category with ID %s not found".formatted(categoryRequest.id()));
        }
        Category category = categoryMapper.toCategory(categoryRequest);
        category.setId(categoryRequest.id());
        categoryRepository.save(category);
        return category.getId();
    }

    public CategoryResponse getCategoryById(Integer id) {
        return categoryRepository.findById(id).map(category -> categoryMapper.toCategoryResponse(category))
                .orElseThrow(
                    () -> new CategoryException("Category with id %s not found".formatted(id))
                );
    }

}
