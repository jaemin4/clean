package com.clean.infra.stock;

import com.clean.domain.stock.Stock;
import com.clean.domain.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class StockJpaRepositoryImpl implements StockRepository {

    private final StockJpaRepository stockJpaRepository;

    @Override
    public Stock findByProductId(Long productId) {
        return stockJpaRepository.findByProductId(productId);
    }
}
