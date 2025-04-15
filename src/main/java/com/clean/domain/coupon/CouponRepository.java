package com.clean.domain.coupon;

public interface CouponRepository {
    Coupon save(Coupon coupon);
    Coupon update(Coupon coupon);
    Coupon findByCouponId(long id);
}
