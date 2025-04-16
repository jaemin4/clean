package com.clean.domain.coupon;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public interface CouponRepository {
    Coupon save(Coupon coupon);
    Optional<Coupon> findById(long couponId);
}
