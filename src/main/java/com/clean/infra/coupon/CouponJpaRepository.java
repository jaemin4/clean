package com.clean.infra.coupon;

import com.clean.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {

}
