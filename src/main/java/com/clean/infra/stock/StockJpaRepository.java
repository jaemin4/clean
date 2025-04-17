package com.clean.infra.stock;

import com.clean.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface StockJpaRepository extends JpaRepository<Stock, Long> {
    Stock findByProductId(Long productId);
}
