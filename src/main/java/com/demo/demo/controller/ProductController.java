package com.demo.demo.controller;


import com.demo.demo.model.Product;
import com.demo.demo.services.ProductSevice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    @Autowired
    private final ProductSevice productSevice;

    @PostMapping("/")
    public Product create(@RequestBody Product product) {
        return productSevice.create(product);
    }

    @GetMapping("/")
    public List<Product> getProducts() {
        return productSevice.getAllProducts();
    }
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productSevice.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productSevice.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public Product delete(@PathVariable Long id) {
        return productSevice.deleteProduct(id);
    }

}
