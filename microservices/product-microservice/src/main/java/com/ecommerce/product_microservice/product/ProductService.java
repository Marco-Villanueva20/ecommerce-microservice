package com.ecommerce.product_microservice.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.product_microservice.exception.ProductException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse> getProducts(){
        return productRepository.findAll()
        .stream()
        .map(product -> ProductMapper.toProductResponse(product)).toList();
    }

    public ProductResponse getProductById(Integer id){
        Product product = productRepository.findById(id).orElseThrow();
        return ProductMapper.toProductResponse(product);
    }

    public Integer createProduct(ProductRequest productRequest){
        Product product = ProductMapper.toProduct(productRequest);
        productRepository.save(product);
        return product.getId();
    }

    public Integer updateProduct(ProductRequest productRequest){
        Product productUpdate = ProductMapper.toProduct(productRequest);
        productUpdate.setId(productRequest.id());
        productRepository.save(productUpdate);
        return productUpdate.getId();
    }

    public void deleteProductById(Integer id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product with id %s not found".formatted(id));
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void purchaseProduct(List<ProductQuantityRequest> productsQuantityRequests) {
        for (ProductQuantityRequest item : productsQuantityRequests) {
            Product product = productRepository.findById(item.productId()).orElseThrow(
                ()-> new ProductException("Product with id %s not found".formatted(item.productId()))
            );
            if (item.quantity() < 0) {
                throw new ProductException("Quantity cannot be negative for product with id %s".formatted(item.productId()));
            }
            
            if (product.getStock() < item.quantity()) {
                throw new ProductException("Insufficient stock for product with id %s".formatted(item.productId()));
            }

            product.setStock(product.getStock() - item.quantity());
            productRepository.save(product);
        }
    }

    @Transactional
    public void restockProduct(List<ProductQuantityRequest> productsQuantityRequests) {
        for (ProductQuantityRequest item : productsQuantityRequests){
            Product product = productRepository.findById(item.productId()).orElseThrow(
                () -> new ProductException("Product with ID %s not found".formatted(item.productId()))
            );
            if (item.quantity()<0) {
                throw new ProductException("Restock quantity cannot be negative for product with ID %s".formatted(item.productId()));             
            }
            product.setStock(product.getStock() + item.quantity());
            productRepository.save(product);
        }
    }

}
