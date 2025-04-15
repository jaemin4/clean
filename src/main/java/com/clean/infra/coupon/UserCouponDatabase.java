package com.clean.infra.coupon;

import com.clean.domain.coupon.UserCoupon;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserCouponDatabase {
    private final Map<Long, UserCoupon> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public UserCoupon save(UserCoupon userCoupon) {
        Long id = idGenerator.getAndIncrement();
        userCoupon.setCouponId(id);
        localDb.put(id, userCoupon);
        return localDb.get(id);
    }

    public UserCoupon update(UserCoupon userCoupon) {
        localDb.put(userCoupon.getCouponId(), userCoupon);
        return localDb.get(userCoupon.getCouponId());
    }

    public boolean existsByUserIdAndCouponId(long userId, long couponId) {
        return localDb.values().stream()
                .anyMatch(c -> c.getUserId() == userId && c.getCouponId() == couponId);
    }

    public long count() {
        return localDb.size();
    }

    public List<Long> getAllIssuedUserIdsInOrder() {
        return localDb.values().stream()
                .sorted(Comparator.comparing(UserCoupon::getIssuedAt))
                .map(UserCoupon::getUserId)
                .toList();
    }


}
