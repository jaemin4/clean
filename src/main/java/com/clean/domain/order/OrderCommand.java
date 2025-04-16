//package com.clean.domain.order;
//
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import java.util.List;
//
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public class OrderCommand {
//
//    @Getter
//    public static class CreateOrder {
//        private final long userId;
//        private final long couponId;
//        private final double discountRate;
//        private final List<OrderItem> items;
//
//        private CreateOrder(long userId, long couponId, double discountRate, List<OrderItem> items) {
//            this.userId = userId;
//            this.couponId = couponId;
//            this.discountRate = discountRate;
//            this.items = items;
//        }
//
//        public static CreateOrder of(long userId, long couponId, double discountRate, List<OrderItem> items) {
//            return new CreateOrder(userId, couponId, discountRate, items);
//        }
//
//    }
//
//}
