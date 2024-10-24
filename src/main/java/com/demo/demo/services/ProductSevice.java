package com.demo.demo.services;

import com.demo.demo.Exception.NotFoundException;
import com.demo.demo.model.Product;
import com.demo.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductSevice {
    @Autowired
    private final ProductRepository productRepository;


    public Product create(Product product) {
        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id).map(p -> {
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());
            p.setName(product.getName());
            return productRepository.save(p);
        }).orElseThrow(() -> new NotFoundException("Product not found"));
    }


    public Product deleteProduct(Long id) {
        productRepository.deleteById(id);
        return null;
    }


    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
