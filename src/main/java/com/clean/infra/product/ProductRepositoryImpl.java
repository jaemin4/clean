package com.clean.infra.product;

import com.clean.domain.product.Product;
import com.clean.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public List<Product> findAllByIdIn(List<Long> productIds) {
        return productJpaRepository.findAllByIdIn(productIds);
    }


}
