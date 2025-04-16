//package com.clean.application.order;
//
//import com.clean.domain.coupon.CouponService;
//import com.clean.domain.order.OrderInfo;
//import com.clean.domain.order.OrderService;
//import com.clean.domain.product.ProductService;
//import com.clean.domain.stock.StockService;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//@RequiredArgsConstructor
//public class OrderFacade {
//
//    private final OrderService orderService;
//    private final StockService stockService;
//    private final ProductService productService;
//    private final CouponService couponService;
//
//    @Transactional
//    public OrderResult.Order order(OrderCriteria.Order criteria) {
//        // todo 재고 유효성 검사
//        productService.findSellingProductsByIds(criteria.toProductsCommand());
//
//        // todo 재고 차감
//        stockService.deductStock(criteria.toDeductStockCommand());
//
//        // todo 쿠폰 사용
//        couponService.use(criteria.toCouponUseCommand());
//
//        // todo 적용 쿠폰 가져오기
//        double discountRate = couponService.getDiscountRate(criteria.toGetDiscountRateCommand());
//
//        // todo 주문 생성
//        OrderInfo.CreateOrder orderInfo = orderService.createOrder(criteria.toCreateOrderCommand(discountRate));
//
//        // todo Result Set
//        return OrderResult.Order.of(
//                orderInfo.getOrderId(),
//                orderInfo.getOrderItems(),
//                orderInfo.getOrderStatus(),
//                orderInfo.getTotalPrice(),
//                orderInfo.getDiscountPrice()
//        );
//    }
//}
