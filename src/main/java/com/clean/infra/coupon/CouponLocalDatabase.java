package com.clean.infra.coupon;

import com.clean.domain.coupon.Coupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class CouponLocalDatabase {
    private final Map<Long, Coupon> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Coupon save(Coupon coupon) {
        Long id = idGenerator.getAndIncrement();
        coupon.setCouponId(id);
        localDb.put(id, coupon);
        return localDb.get(id);
    }

    public Coupon update(Coupon coupon) {
        localDb.put(coupon.getCouponId(), coupon);
        return localDb.get(coupon.getCouponId());
    }

    public Coupon findByCouponId(long id) {
        return localDb.get(id);
    }
}
