package com.clean.application;

import com.clean.domain.balance.Balance;
import com.clean.domain.balance.BalanceService;
import com.clean.domain.order.Order;
import com.clean.domain.order.OrderItem;
import com.clean.domain.order.OrderService;
import com.clean.domain.payment.PaymentHistoryService;
import com.clean.domain.product.ProductService;
import com.clean.infra.payment.PaymentMockService;
import com.clean.interfaces.model.dto.req.ReqCalculateTotalAmountDto;
import com.clean.interfaces.model.dto.req.ReqPaymentHistoryDto;
import com.clean.interfaces.model.dto.req.ReqRecordBalanceHistoryDto;
import com.clean.interfaces.model.dto.req.ReqUseBalanceDto;
import com.clean.interfaces.model.dto.res.ResCompletePaymentDto;
import com.clean.interfaces.model.param.PaymentMockParam;
import com.clean.interfaces.model.param.PaymentParam;
import com.clean.interfaces.model.rest.MockPaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentFrontService {

    private final BalanceService balanceService;
    private final PaymentHistoryService paymentHistoryService;
    private final ProductService productService;
    private final OrderService orderService;
    private final PaymentMockService paymentMockService;

    public ResCompletePaymentDto completePayment(PaymentParam param) {
        final Long paramOrderId = param.getOrderId();
        final Long paramUserId = param.getUserId();

        // TODO 총 결제액 계산
        Order order = orderService.getOrderById(paramOrderId);
        List<OrderItem> orderItems = order.getItems();

        List<ReqCalculateTotalAmountDto> amountItems = orderItems.stream()
                .map(item -> new ReqCalculateTotalAmountDto(item.getProductId(), item.getQuantity()))
                .toList();

        long totalAmount = productService.calculateTotalAmount(amountItems);

        // TODO 잔액 사용 + 유저 유효성
        Balance balance = balanceService.usableBalance(new ReqUseBalanceDto(
                paramUserId,totalAmount)
        );

        // TODO 잔액 사용내역 저장
        balanceService.recordBalanceHistory(new ReqRecordBalanceHistoryDto(
                paramUserId,totalAmount * (-1))
        );

        // todo 결제 API 호출 및 검증
       MockPaymentResponse mockPaymentResponse = paymentMockService.callAndValidateMockApi(new PaymentMockParam(
               paramOrderId, paramUserId, totalAmount)
       );
       String transactionId =  mockPaymentResponse.getTransactionId();
       String status = mockPaymentResponse.getStatus();

        // TODO 결제내역 저장
        paymentHistoryService.recordPaymentHistory(new ReqPaymentHistoryDto(
                paramUserId,totalAmount * (-1), paramOrderId,transactionId,status)
        );

        return new ResCompletePaymentDto(
                paramOrderId, paramUserId,totalAmount,orderItems
        );
    }



}
