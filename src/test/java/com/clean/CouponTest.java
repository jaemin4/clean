package com.clean;

import com.clean.domain.coupon.Coupon;
import com.clean.domain.coupon.CouponService;
import com.clean.infra.coupon.CouponLocalDatabase;
import com.clean.infra.coupon.UserCouponDatabase;
import com.clean.interfaces.model.param.IssueCouponParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CouponTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponLocalDatabase couponLocalDatabase;

    @Autowired
    private UserCouponDatabase userCouponDatabase;

    @BeforeEach
    void setUp() {
        // todo 쿠폰 등록 (수량 5장)
        Coupon coupon = new Coupon();
        coupon.setTitle("10% 할인 쿠폰");
        coupon.setDiscountType("percentage");
        coupon.setDiscountValue(10);

        // todo 최대 발급 수량
        coupon.setCouponQuantity(3);
        coupon.setStartDateTime(LocalDateTime.now().minusDays(1));
        coupon.setEndDateTime(LocalDateTime.now().plusDays(1));

        couponLocalDatabase.save(coupon);
    }
    @DisplayName("쿠폰 중복발급시 예외가 발생하는지")
    @Test
    void userDuplicateCoupon() {
        Long userId = 1L;
        Long couponId = 1L;
        couponService.issueCoupon(new IssueCouponParam(userId, couponId));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            couponService.issueCoupon(new IssueCouponParam(userId, couponId));
        });

        assertEquals("이미 발급받은 쿠폰입니다. userId=" + userId,exception.getMessage());
    }

    @DisplayName("쿠폰이 정상적으로 사용자에게 발급되는지")
    @Test
    void createCoupon() {
        Long userId = 1L;
        Long couponId = 1L;
        couponService.issueCoupon(new IssueCouponParam(userId, couponId));
        Boolean result = userCouponDatabase.existsByUserIdAndCouponId(userId, couponId);
        assertEquals(true,result);
    }

    @DisplayName("쿠폰이 정상적으로 차감되었는지")
    @Test
    void couponQuantityShouldDecreaseAfterIssue() {
        Long userId = 1L;
        Long couponId = 1L;

        int before = couponLocalDatabase.findByCouponId(couponId).getCouponQuantity();
        couponService.issueCoupon(new IssueCouponParam(userId, couponId));

        int after = couponLocalDatabase.findByCouponId(couponId).getCouponQuantity();
        assertEquals(before - 1, after, "쿠폰 수량은 1 감소해야 합니다");
    }

    @DisplayName("쿠폰이 모두 소모되었을 때 예외가 발생하는지")
    @Test
    void shouldThrowExceptionWhenCouponDepleted() {
        Long couponId = 2L;
        Long user1 = 1L;
        Long user2 = 2L;

        Coupon coupon = new Coupon();
        coupon.setTitle("테스트 쿠폰");
        coupon.setDiscountType("fixed");
        coupon.setDiscountValue(1000);
        coupon.setCouponQuantity(1);
        coupon.setStartDateTime(LocalDateTime.now().minusDays(1));
        coupon.setEndDateTime(LocalDateTime.now().plusDays(1));
        couponLocalDatabase.save(coupon);

        // when: 첫 번째 사용자 발급 성공
        couponService.issueCoupon(new IssueCouponParam(user1, couponId));

        // then: 두 번째 사용자 발급 시 RuntimeException 발생해야 함
        assertThrows(RuntimeException.class, () -> {
            couponService.issueCoupon(new IssueCouponParam(user2, couponId));
        }, "쿠폰 수량이 부족하면 예외가 발생해야 함");
    }

}
