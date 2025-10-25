package com.ecommerce.product_microservice.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createProduct(@Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PutMapping
    public ResponseEntity<Integer> updateProduct(@Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.updateProduct(productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer id){
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/purchase")
    public ResponseEntity<Void> purchaseProduct(@Valid @RequestBody List<ProductQuantityRequest> productsQuantityRequests) {
        productService.purchaseProduct(productsQuantityRequests);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/restock")
    public ResponseEntity<Void> updateProductStock(@Valid @RequestBody List<ProductQuantityRequest> productsQuantityRequests) {
        productService.restockProduct(productsQuantityRequests);
        return ResponseEntity.ok().build();
    }
    



}
