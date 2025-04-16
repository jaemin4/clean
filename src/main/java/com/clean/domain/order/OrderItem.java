//package com.clean.domain.order;
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "order_item")
//public class OrderItem {
//
//    @Id
//    @Column(name = "order_item_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Setter
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", nullable = false)
//    private OrderProduct orderProduct;
//
//    @Column(nullable = false)
//    private Long productId;
//
//    @Column(nullable = false)
//    private String productName;
//
//    @Column(nullable = false)
//    private long unitPrice;
//
//    @Column(nullable = false)
//    private int quantity;
//
//    private OrderItem(Long productId, String productName, long unitPrice, int quantity) {
//        this.productId = productId;
//        this.productName = productName;
//        this.unitPrice = unitPrice;
//        this.quantity = quantity;
//    }
//
//    public static OrderItem create(Long productId, String productName, long unitPrice, int quantity) {
//        return new OrderItem(productId, productName, unitPrice, quantity);
//    }
//
//    public long getPrice() {
//        return unitPrice * quantity;
//    }
//}
