package com.clean.domain.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    private Long couponId;
    private String title;
    private String discountType; // "fixed"
    private Integer discountValue;// "10%"
    private Integer couponQuantity;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
