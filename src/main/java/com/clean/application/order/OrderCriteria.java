//package com.clean.application.order;
//
//import com.clean.domain.coupon.CouponCommand;
//import com.clean.domain.order.OrderCommand;
//import com.clean.domain.order.OrderItem;
//import com.clean.domain.product.ProductCommand;
//import com.clean.domain.stock.StockCommand;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import java.util.List;
//
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public class OrderCriteria {
//
//    @Getter
//    public static class Order{
//        private final long userId;
//        private final long couponId;
//        private final List<OrderItem> items;
//
//        private Order(Long userId, long couponId, List<OrderItem> items) {
//            this.userId = userId;
//            this.couponId = couponId;
//            this.items = items;
//        }
//
//        public StockCommand.deductStock toDeductStockCommand() {
//            return StockCommand.deductStock.of(items);
//        }
//
//        public OrderCommand.CreateOrder toCreateOrderCommand(double discountRate) {
//            return OrderCommand.CreateOrder.of(
//                    userId,
//                    couponId,
//                    discountRate,
//                    items
//            );
//        }
//
//        public CouponCommand.Use toCouponUseCommand() {
//            return CouponCommand.Use.of( userId,couponId);
//        }
//
//        public ProductCommand.Products toProductsCommand() {
//            List<Long> productIds = items.stream()
//                    .map(OrderItem::getProductId)
//                    .toList();
//
//            return ProductCommand.Products.of(productIds);
//        }
//
//        public CouponCommand.GetDiscountRate toGetDiscountRateCommand() {
//            return CouponCommand.GetDiscountRate.of(couponId);
//        }
//
//        public static Order of(Long userId, long couponId, List<OrderItem> items) {
//            return new Order(userId, couponId,items);
//        }
//
//
//    }
//
//}
