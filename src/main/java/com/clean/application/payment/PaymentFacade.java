//package com.clean.application.payment;
//
//import com.clean.domain.balance.BalanceService;
//import com.clean.domain.order.OrderProduct;
//import com.clean.domain.order.OrderService;
//import com.clean.domain.payment.PaymentHistoryService;
//import com.clean.infra.payment.MockPaymentService;
//import com.clean.infra.payment.PaymentMockResponse;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class PaymentFacade {
//
//    private final PaymentHistoryService paymentHistoryService;
//    private final OrderService orderService;
//    private final BalanceService balanceService;
//    private final MockPaymentService mockPaymentService;
//
//    @Transactional
//    public void pay(PaymentCriteria.Payment criteria) {
//
//        OrderProduct orderProduct = orderService.getOrderById(criteria.getOrderId());
//
//        // todo 결제 API 호출 및 검증
//       PaymentMockResponse mockPaymentResponse = mockPaymentService.callAndValidateMockApi(
//               criteria.toPaymentMockRequest(orderProduct.getTotalPrice())
//       );
//
//       String transactionId = mockPaymentResponse.getTransactionId();
//       String status = mockPaymentResponse.getStatus();
//
//        // todo 잔액차감 및 사용내역 저장
//       balanceService.use(criteria.toBalanceUseCommand());
//
//        // TODO 결제내역 저장
//       paymentHistoryService.recordPaymentHistory(
//                criteria.toPaymentHistoryCommand(transactionId,status, orderProduct.getId())
//       );
//
//    }
//
//
//
//}
