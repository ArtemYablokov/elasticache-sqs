package com.yablokovs.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.yablokovs.model.Product;
import com.yablokovs.repository.ProductRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@XRayEnabled
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product findProductById(Long id) {
        Optional<Product> optionalProductById = repository.findById(id);
        return optionalProductById.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product putProduct(Product product) {
        Long id = product.getId();
        return repository.save(product);
    }
}