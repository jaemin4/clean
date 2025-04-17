package com.clean.infra.coupon;

import com.clean.domain.coupon.UserCoupon;
import com.clean.domain.coupon.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCouponRepositoryImpl implements UserCouponRepository {

    private final UserCouponJpaRepository userCouponJpaRepository;

    @Override
    public UserCoupon save(UserCoupon coupon) {
        return userCouponJpaRepository.save(coupon);
    }

    @Override
    public Optional<UserCoupon> findById(long id) {
        return userCouponJpaRepository.findById(id);
    }
}
