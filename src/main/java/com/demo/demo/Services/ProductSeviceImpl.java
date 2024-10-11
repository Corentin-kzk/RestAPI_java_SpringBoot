package com.demo.demo.Services;

import com.demo.demo.model.Product;
import com.demo.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductSeviceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id).map(p -> {
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());
            p.setName(product.getName());
            return productRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product deleteProduct(Long id) {
        productRepository.deleteById(id);
        return null;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
