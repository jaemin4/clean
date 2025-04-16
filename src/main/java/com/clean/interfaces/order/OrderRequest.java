//package com.clean.interfaces.order;
//
//import com.clean.application.order.OrderCriteria;
//import com.clean.domain.order.OrderItem;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Positive;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import java.util.List;
//
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public class OrderRequest {
//
//    @Getter
//    @NoArgsConstructor
//    public static class Order{
//        @NotNull
//        @Positive
//        private Long userId;
//
//        @NotNull
//        @Positive
//        private Long couponId;
//
//        @Valid
//        @NotEmpty
//        private List<OrderItem> items;
//
//        public OrderCriteria.Order toCriteria() {
//            return OrderCriteria.Order.of(userId, couponId, items);
//        }
//
//
//    }
//}
