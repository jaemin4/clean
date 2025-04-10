package com.clean.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findByProductId(Long productId) {
        return productRepository.findByProductId(productId).orElseThrow(
                () -> new RuntimeException("Product not found : "+ productId)
        );
    }

}
