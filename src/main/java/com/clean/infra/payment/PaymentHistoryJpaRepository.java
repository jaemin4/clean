package com.clean.infra.payment;

import com.clean.domain.payment.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface PaymentHistoryJpaRepository extends JpaRepository<PaymentHistory, Long> {


}
