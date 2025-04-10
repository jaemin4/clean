package com.clean.infra.product;

import com.clean.domain.product.Product;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductLocalDatabase {

    private final Map<Long, Product> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Optional<Product> findByProductId(Long productId) {
        if (productId == null) return Optional.empty();

        return localDb.values().stream()
                .filter(product -> productId.equals(product.getProductId()))
                .findFirst();
    }


}
