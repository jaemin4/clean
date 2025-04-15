package com.clean.infra.payment;

import com.clean.domain.payment.PaymentHistory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class PaymentHistoryLocalDatabase {

    private final Map<Long, PaymentHistory> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public void save(PaymentHistory paymentHistory) {
        final Long id = idGenerator.getAndIncrement();
        paymentHistory.setPaymentHistoryId(id);
        localDb.put(id, paymentHistory);
    }

    public List<PaymentHistory> findAll() {
        return new ArrayList<>(localDb.values());
    }
}
