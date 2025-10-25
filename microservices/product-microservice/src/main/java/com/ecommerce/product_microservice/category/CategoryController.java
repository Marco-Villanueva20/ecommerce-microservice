package com.ecommerce.product_microservice.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PostMapping
    public ResponseEntity<Integer> postCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Integer id) throws Exception{
        categoryService.deleteCategoryById(id);
        return ResponseEntity.accepted().build();
    }        

    @PutMapping()
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategory(categoryRequest);
        
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    
    

}
