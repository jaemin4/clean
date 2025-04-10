package com.clean.infra.coupon;

import com.clean.domain.coupon.UserCoupon;
import com.clean.domain.coupon.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCouponRepositoryImpl implements UserCouponRepository {

    private final UserCouponDatabase userCouponDatabase;

    @Override
    public UserCoupon save(UserCoupon coupon) {
        return userCouponDatabase.save(coupon);
    }

    @Override
    public UserCoupon update(UserCoupon coupon) {
        return userCouponDatabase.update(coupon);
    }

    @Override
    public boolean existsByUserIdAndCouponId(long userId, long couponId) {
        return userCouponDatabase.existsByUserIdAndCouponId(userId, couponId);
    }
}
