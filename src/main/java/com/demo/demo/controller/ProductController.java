package com.demo.demo.controller;


import com.demo.demo.Services.ProductService;
import com.demo.demo.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("/")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/delete/{id}")
    public Product delete(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
