package com.clean.infra.order;

import com.clean.domain.order.Order;
import com.clean.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderLocalDatabase orderLocalDatabase;

    @Override
    public Order save(Order order) {
        return orderLocalDatabase.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderLocalDatabase.getOrderById(orderId);
    }
}
