package com.clean.domain.stock;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface StockRepository {
    Stock findByProductId(Long productId);
}
