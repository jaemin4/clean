package com.clean.domain.product;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Component
public interface ProductRepository {
    Optional<Product> findById(Long productId);
    List<Product> findAllByIdIn(List<Long> productIds);
}
