//package com.clean.domain.stock;
//
//import com.clean.domain.order.OrderItem;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import java.util.List;
//
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public class StockCommand {
//    @Getter
//    public static class deductStock {
//
//        private final List<OrderItem> products;
//
//        private deductStock(List<OrderItem> products) {
//            this.products = products;
//        }
//
//        public static deductStock of(List<OrderItem> products) {
//            return new deductStock(products);
//        }
//    }
//
//
//}
