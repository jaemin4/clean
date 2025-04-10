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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    @Test
    @DisplayName("선착순 쿠폰 발급")
    void testConcurrentCouponIssueWithOrder() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Long couponId = 1L;

        for (int i = 0; i < threadCount; i++) {
            final long userId = i;
            executor.execute(() -> {
                try {
                    IssueCouponParam param = new IssueCouponParam(userId, couponId);
                    couponService.issueCoupon(param);
                } catch (Exception ignored) {
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        long totalIssued = userCouponDatabase.count();
        assertThat(totalIssued).isEqualTo(3);

        System.out.println("발급 순서:");
        userCouponDatabase.getAllIssuedUserIdsInOrder().forEach(System.out::println);
    }
}
