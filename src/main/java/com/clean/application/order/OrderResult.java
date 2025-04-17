//package com.clean.application.order;
//
//import com.clean.domain.order.OrderItem;
//import com.clean.domain.order.OrderStatus;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
//public class OrderResult {
//
//    @Getter
//    public static class Order {
//        private final Long orderId;
//        private final List<OrderItem> orderItems;
//        private final OrderStatus status;
//        private final long totalPrice;
//        private final long discountPrice;
//
//        private Order(Long orderId, List<OrderItem> orderItems, OrderStatus status, long totalPrice, long discountPrice) {
//            this.orderId = orderId;
//            this.orderItems = orderItems;
//            this.status = status;
//            this.totalPrice = totalPrice;
//            this.discountPrice = discountPrice;
//        }
//
//        public static Order of(Long orderId, List<OrderItem> orderItems, OrderStatus status, long totalPrice, long discountPrice) {
//            return new Order(orderId, orderItems, status, totalPrice, discountPrice);
//        }
//    }
//}
