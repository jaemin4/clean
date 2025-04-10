package com.clean.domain.coupon;

import com.clean.interfaces.model.dto.res.ResIssueCouponDto;
import com.clean.interfaces.model.param.IssueCouponParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;
    private final Map<Long, Lock> couponLocks = new ConcurrentHashMap<>();

    public ResIssueCouponDto issueCoupon(IssueCouponParam param) {
        final long userId = param.getUserId();
        final long couponId = param.getCouponId();

        // todo 공정 락 생성 및 획득
        Lock lock = couponLocks.computeIfAbsent(couponId, k -> new ReentrantLock(true));
        lock.lock();

        try {
            // todo 쿠폰 존재 및 재고 확인
            Coupon coupon = couponRepository.findByCouponId(couponId);
            if (coupon == null) {
                throw new RuntimeException("해당 쿠폰이 존재하지 않습니다. couponId=" + couponId);
            }

            if (coupon.getCouponQuantity() < 1) {
                throw new RuntimeException("쿠폰이 모두 소진되었습니다. couponId=" + couponId);
            }

            // todo 중복 발급 확인
            if (userCouponRepository.existsByUserIdAndCouponId(userId, couponId)) {
                throw new RuntimeException("이미 발급받은 쿠폰입니다. userId=" + userId);
            }

            // todo 쿠폰 수량 차감
            coupon.setCouponQuantity(coupon.getCouponQuantity() - 1);
            couponRepository.update(coupon);

            // todo 사용자 쿠폰 저장
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setCouponId(couponId);
            userCoupon.setUserId(userId);
            userCoupon.setUsed(false);
            userCoupon.setIssuedAt(LocalDateTime.now());
            userCoupon.setUsedAt(null);

            userCouponRepository.save(userCoupon);

            log.info(" 쿠폰 발급 완료 - userId: {}, couponId: {}", userId, couponId);

            return new ResIssueCouponDto(
                    coupon.getTitle(),
                    coupon.getDiscountType(),
                    coupon.getDiscountValue()
            );

        } catch (RuntimeException e) {
            log.error("❌ 쿠폰 발급 실패: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("❌ 쿠폰 발급 처리 중 예상치 못한 오류 발생", e);
            throw new RuntimeException("쿠폰 발급 중 예외 발생", e);
        } finally {
            lock.unlock();
        }
    }
}