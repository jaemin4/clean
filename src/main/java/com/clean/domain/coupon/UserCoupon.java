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
public class UserCoupon {

    private Long userCouponId;
    private Long userId;
    private Long couponId;
    private Boolean used;
    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;

}
