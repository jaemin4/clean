package com.clean.domain.coupon;

import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserCouponRepository {
    UserCoupon save(UserCoupon coupon);
    Optional<UserCoupon> findById(long id);
}
