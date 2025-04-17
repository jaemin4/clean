package com.clean.infra.payment;

import com.clean.domain.payment.PaymentHistory;
import com.clean.domain.payment.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentHistoryRepository {

    private final PaymentHistoryJpaRepository paymentLocalRepository;

    @Override
    public void save(PaymentHistory paymentHistory) {
        paymentLocalRepository.save(paymentHistory);
    }
}
