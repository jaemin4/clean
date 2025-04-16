package com.clean.domain.payment;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface PaymentHistoryRepository {
    void save(PaymentHistory paymentHistory);
}
