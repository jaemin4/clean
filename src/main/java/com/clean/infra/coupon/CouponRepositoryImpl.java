package com.clean.infra.coupon;

import com.clean.domain.coupon.Coupon;
import com.clean.domain.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final CouponLocalDatabase couponLocalDatabase;

    @Override
    public Coupon save(Coupon coupon) {
        return couponLocalDatabase.save(coupon);
    }

    @Override
    public Coupon update(Coupon coupon) {
        return couponLocalDatabase.update(coupon);
    }

    @Override
    public Coupon findByCouponId(long couponId) {
        return couponLocalDatabase.findByCouponId(couponId);
    }
}
