package com.clean.domain.payment;

import com.clean.interfaces.model.dto.req.ReqPaymentHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;

    public void recordPaymentHistory(ReqPaymentHistoryDto DTO) {
        paymentHistoryRepository.save(new PaymentHistory(
                DTO.getUserId(),
                DTO.getAmount(),
                DTO.getOrderId(),
                DTO.getTransactionId(),
                DTO.getStatus()
        ));
    }


}
