package com.demo.demo.services;

import com.demo.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product product);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    Product deleteProduct(Long id);
    Optional<Product> getProductById(Long id);
}
