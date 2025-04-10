package com.clean.domain.coupon;

public interface UserCouponRepository {
    UserCoupon save(UserCoupon coupon);
    UserCoupon update(UserCoupon coupon);
    boolean existsByUserIdAndCouponId(long reqUserId, long reqCouponId);
}
