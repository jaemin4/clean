package com.clean.infra.coupon;

import com.clean.domain.coupon.Coupon;
import com.clean.domain.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final CouponJpaRepository couponJpaRepository;

    @Override
    public Coupon save(Coupon coupon) {
        return couponJpaRepository.save(coupon);
    }

    @Override
    public Optional<Coupon> findById(long id) {
        return couponJpaRepository.findById(id);
    }
}
