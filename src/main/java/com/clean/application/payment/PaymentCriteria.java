package com.clean.application.payment;

import com.clean.domain.balance.BalanceCommand;
import com.clean.domain.payment.PaymentHistoryCommand;
import com.clean.infra.payment.PaymentMockRequest;
import com.clean.infra.payment.PaymentMockResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentCriteria {

    @Getter
    public static class Payment {
        private Long orderId;
        private Long userId;

        private Payment(Long orderId, Long userId) {
            this.orderId = orderId;
            this.userId = userId;
        }

        public static Payment of(Long orderId, Long userId) {
            return new Payment(orderId, userId);
        }

        public BalanceCommand.Use toBalanceUseCommand(){
            return BalanceCommand.Use.of(orderId, userId);
        }

        public PaymentMockRequest.Mock toPaymentMockRequest(Long amount) {
            return PaymentMockRequest.Mock.of(orderId,userId,amount);
        }

        public PaymentHistoryCommand.Save toPaymentHistoryCommand(String transactionId,String status,Long amount ) {
            return PaymentHistoryCommand.Save.of(userId,amount,orderId,transactionId,status);
        }



    }

}
