//package com.clean.domain.stock;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class StockService {
//    private final StockRepository stockRepository;
//
//    public void deductStock(StockCommand.deductStock command) {
//        command.getProducts().forEach(product -> {
//            Stock stock = stockRepository.findByProductId(product.getId());
//            stock.deduct(product.getQuantity());
//        });
//    }
//
////    public StockInfo getStock(Long productId) {
////        Stock stock = stockRepository.findByProductId(productId);
////        return StockInfo.of(stock.getId(), stock.getQuantity());
////    }
//}
