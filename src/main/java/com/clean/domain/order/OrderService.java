//package com.clean.domain.order;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    /**
//     * 주문 생성
//     */
//    public OrderInfo.CreateOrder createOrder(OrderCommand.CreateOrder command) {
//
//        OrderProduct orderProduct = OrderProduct.create(
//                command.getUserId(),
//                command.getCouponId(),
//                command.getDiscountRate(),
//                command.getItems()
//        );
//        orderRepository.save(orderProduct);
//
//        return OrderInfo.CreateOrder.of(
//                orderProduct.getId(),
//                orderProduct.getTotalPrice(),
//                orderProduct.getDiscountPrice(),
//                orderProduct.getOrderItems(),
//                orderProduct.getOrderStatus()
//        );
//    }
//
//    /**
//     * 주문 단건 조회
//     */
//    public OrderProduct getOrderById(Long orderId) {
//        return orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("해당 주문이 존재하지 않습니다. orderId=" + orderId));
//    }
//}
