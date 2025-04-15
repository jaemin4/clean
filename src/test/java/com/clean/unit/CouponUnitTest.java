package com.clean.unit;

import com.clean.domain.coupon.Coupon;
import com.clean.domain.coupon.CouponService;
import com.clean.domain.coupon.CouponRepository;
import com.clean.domain.coupon.UserCouponRepository;
import com.clean.interfaces.model.param.IssueCouponParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CouponUnitTest {
    private CouponService couponService;
    private CouponRepository couponRepository;
    private UserCouponRepository userCouponRepository;

    @BeforeEach
    void setUp() {
        couponRepository = mock(CouponRepository.class);
        userCouponRepository = mock(UserCouponRepository.class);
        couponService = new CouponService(couponRepository, userCouponRepository);
    }

    @Test
    @DisplayName("쿠폰이 이미 발급된 경우 예외를 발생시킨다")
    void userDuplicateCoupon() {
        Long userId = 1L;
        Long couponId = 1L;

        Coupon coupon = new Coupon();
        coupon.setCouponId(couponId);
        coupon.setCouponQuantity(3);
        coupon.setStartDateTime(LocalDateTime.now().minusDays(1));
        coupon.setEndDateTime(LocalDateTime.now().plusDays(1));

        when(couponRepository.findByCouponId(couponId)).thenReturn(coupon);
        when(userCouponRepository.existsByUserIdAndCouponId(userId, couponId)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            couponService.issueCoupon(new IssueCouponParam(userId, couponId));
        });

        assertEquals("이미 발급받은 쿠폰입니다. userId=" + userId, exception.getMessage());
    }

    @Test
    @DisplayName("쿠폰 발급 시 쿠폰 수량이 감소되어야 한다")
    void couponQuantityShouldDecreaseAfterIssue() {
        Long userId = 1L;
        Long couponId = 1L;

        Coupon coupon = new Coupon();
        coupon.setCouponId(couponId);
        coupon.setCouponQuantity(3);
        coupon.setStartDateTime(LocalDateTime.now().minusDays(1));
        coupon.setEndDateTime(LocalDateTime.now().plusDays(1));

        when(couponRepository.findByCouponId(couponId)).thenReturn(coupon);
        when(userCouponRepository.existsByUserIdAndCouponId(userId, couponId)).thenReturn(false);

        couponService.issueCoupon(new IssueCouponParam(userId, couponId));

        assertEquals(2, coupon.getCouponQuantity());
    }

    @Test
    @DisplayName("쿠폰 수량이 0이면 예외가 발생한다")
    void shouldThrowExceptionWhenCouponDepleted() {
        Long userId = 1L;
        Long couponId = 1L;

        Coupon coupon = new Coupon();
        coupon.setCouponId(couponId);
        coupon.setCouponQuantity(0);
        coupon.setStartDateTime(LocalDateTime.now().minusDays(1));
        coupon.setEndDateTime(LocalDateTime.now().plusDays(1));

        when(couponRepository.findByCouponId(couponId)).thenReturn(coupon);
        when(userCouponRepository.existsByUserIdAndCouponId(userId, couponId)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            couponService.issueCoupon(new IssueCouponParam(userId, couponId));
        });

        assertEquals("쿠폰이 모두 소진되었습니다. couponId=" + couponId, exception.getMessage());
    }

    @Test
    @DisplayName("쿠폰이 정상적으로 사용자에게 발급된다")
    void createCoupon() {
        Long userId = 1L;
        Long couponId = 1L;

        Coupon coupon = new Coupon();
        coupon.setCouponId(couponId);
        coupon.setCouponQuantity(3);
        coupon.setStartDateTime(LocalDateTime.now().minusDays(1));
        coupon.setEndDateTime(LocalDateTime.now().plusDays(1));

        when(couponRepository.findByCouponId(couponId)).thenReturn(coupon);
        when(userCouponRepository.existsByUserIdAndCouponId(userId, couponId)).thenReturn(false);

        couponService.issueCoupon(new IssueCouponParam(userId, couponId));

        verify(userCouponRepository, times(1)).save(any());
    }
}
