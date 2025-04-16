//package com.clean.domain.order;
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "t_order")
//public class OrderProduct {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_id")
//    private Long id;
//
//    @Column(nullable = false)
//    private Long userId;
//
//    private Long userCouponId;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private OrderStatus orderStatus;
//
//    @Column(nullable = false)
//    private long totalPrice;
//
//    @Column(nullable = false)
//    private long discountPrice;
//
//    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL, orphanRemoval = true)
//    private final List<OrderItem> orderItems = new ArrayList<>();
//
//    private OrderProduct(Long userId, Long userCouponId, long totalPrice, long discountPrice, OrderStatus orderStatus) {
//        this.userId = userId;
//        this.userCouponId = userCouponId;
//        this.totalPrice = totalPrice;
//        this.discountPrice = discountPrice;
//        this.orderStatus = orderStatus;
//    }
//
//    public static OrderProduct create(Long userId, Long userCouponId, double discountRate, List<OrderItem> orderItems) {
//        validateOrderItems(orderItems);
//        long calculatedTotalPrice = calculateTotalPrice(orderItems);
//        long calculatedDiscountPrice = (long) (calculatedTotalPrice * discountRate);
//        long finalPrice = calculatedTotalPrice - calculatedDiscountPrice;
//
//        OrderProduct orderProduct = new OrderProduct(
//                userId,
//                userCouponId,
//                finalPrice,
//                calculatedDiscountPrice,
//                OrderStatus.CREATED
//        );
//
//        orderItems.forEach(orderProduct::addOrderItem);
//        return orderProduct;
//    }
//
//    public void addOrderItem(OrderItem orderItem) {
//        this.orderItems.add(orderItem);
//        orderItem.setOrderProduct(this);
//    }
//
//    private static void validateOrderItems(List<OrderItem> orderItems) {
//        if (orderItems == null || orderItems.isEmpty()) {
//            throw new IllegalArgumentException("주문 상품이 없습니다.");
//        }
//    }
//
//    private static long calculateTotalPrice(List<OrderItem> orderItems) {
//        return orderItems.stream()
//                .mapToLong(OrderItem::getPrice)
//                .sum();
//    }
//}
