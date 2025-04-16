package com.clean.domain.coupon;

import com.clean.domain.order.AuditableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_coupon")
public class Coupon extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int discountRate; // 예: 10 = 10%

    @Column(nullable = false)
    private Long quantity;

    // 수량 감소 메서드
    public void use() {
        if (quantity <= 0) {
            throw new IllegalStateException("쿠폰 수량이 부족합니다.");
        }
        this.quantity--;
    }

}
