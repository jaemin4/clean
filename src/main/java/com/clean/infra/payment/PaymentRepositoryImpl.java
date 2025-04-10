package com.clean.infra.payment;

import com.clean.domain.payment.PaymentHistory;
import com.clean.domain.payment.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PaymentRepositoryImpl implements PaymentHistoryRepository {

    private final PaymentHistoryLocalDatabase paymentLocalRepository;

    @Override
    public void save(PaymentHistory paymentHistory) {
        paymentLocalRepository.save(paymentHistory);
    }
}
