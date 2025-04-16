//package com.clean.domain.order;
//
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public class OrderInfo {
//
//    @Getter
//    public static class CreateOrder {
//        private final Long orderId;
//        private final long totalPrice;
//        private final long discountPrice;
//        private final List<OrderItem> orderItems;
//        private final OrderStatus orderStatus;
//
//        private CreateOrder(Long orderId, long totalPrice, long discountPrice, List<OrderItem> orderItems, OrderStatus orderStatus) {
//            this.orderId = orderId;
//            this.totalPrice = totalPrice;
//            this.discountPrice = discountPrice;
//            this.orderItems = orderItems;
//            this.orderStatus = orderStatus;
//        }
//
//        public static CreateOrder of(Long orderId, long totalPrice, long discountPrice, List<OrderItem> orderItems,OrderStatus orderStatus) {
//            return new CreateOrder(orderId, totalPrice, discountPrice, orderItems, orderStatus);
//        }
//    }
//
//}