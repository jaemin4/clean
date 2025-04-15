package com.clean.domain.product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findByProductId(Long stockId);

    Product updateProduct(Product product);

}
