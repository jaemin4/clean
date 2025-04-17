//package com.clean.infra.order;
//
//import com.clean.domain.order.OrderProduct;
//import com.clean.domain.order.OrderRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//@RequiredArgsConstructor
//public class OrderRepositoryImpl implements OrderRepository {
//
//    private final OrderJpaRepository orderJpaRepository;
//
//    @Override
//    public OrderProduct save(OrderProduct orderProduct) {
//        return orderJpaRepository.save(orderProduct);
//    }
//
//    @Override
//    public Optional<OrderProduct> findById(Long orderId) {
//        return orderJpaRepository.findById(orderId);
//    }
//}
