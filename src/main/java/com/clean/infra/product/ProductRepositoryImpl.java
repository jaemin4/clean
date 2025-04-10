package com.clean.infra.product;

import com.clean.domain.product.Product;
import com.clean.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductLocalDatabase productLocalDatabase;

    @Override
    public Optional<Product> findByProductId(Long stockId) {
        return productLocalDatabase.findByProductId(stockId);
    }

}
