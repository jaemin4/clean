package com.clean.integration;

import com.clean.application.BalanceFrontService;
import com.clean.application.OrderFrontService;
import com.clean.application.PaymentFrontService;
import com.clean.domain.balance.Balance;
import com.clean.domain.balance.BalanceHistory;
import com.clean.domain.order.Order;
import com.clean.domain.order.OrderItem;
import com.clean.domain.order.OrderService;
import com.clean.domain.product.Product;
import com.clean.domain.product.ProductService;
import com.clean.infra.balance.BalanceHistoryLocalDatabase;
import com.clean.infra.balance.BalanceLocalDatabase;
import com.clean.infra.order.OrderLocalDatabase;
import com.clean.infra.payment.PaymentHistoryLocalDatabase;
import com.clean.infra.product.ProductLocalDatabase;
import com.clean.interfaces.model.dto.req.ReqCalculateTotalAmountDto;
import com.clean.interfaces.model.dto.res.ResCompleteOrderDto;
import com.clean.interfaces.model.param.CompleteChargeParam;
import com.clean.interfaces.model.param.OrderItemParam;
import com.clean.interfaces.model.param.OrderParam;
import com.clean.interfaces.model.param.PaymentParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    BalanceLocalDatabase balanceLocalDatabase;

    @Autowired
    ProductLocalDatabase productLocalDatabase;

    @Autowired
    private BalanceHistoryLocalDatabase balanceHistoryLocalDatabase;

    @Autowired
    private BalanceFrontService balanceFrontService;

    @Autowired
    private OrderFrontService orderFrontService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentFrontService paymentFrontService;

    @Autowired
    private OrderLocalDatabase orderLocalDatabase;

    @Autowired
    private PaymentHistoryLocalDatabase paymentHistoryLocalDatabase;

    /*
  todo
    product(ProductId):1L,2L / Balance(BalanceId,UserId):1L,2L
    각 케이스마다 해당 ID값 Default 저장
*/
    @BeforeEach
    public void beforeEach() {
        Product defaultProduct = new Product("컴퓨터", 1_000_000L, 10L);
        Product defaultProduct2 = new Product( "마우스", 10000L, 10L);
        productLocalDatabase.save(defaultProduct);
        productLocalDatabase.save(defaultProduct2);

        Balance defaultUser = new Balance(1L,100_000_000L);
        Balance defaultUser2 = new Balance(2L,0L);
        balanceLocalDatabase.save(defaultUser);
        balanceLocalDatabase.save(defaultUser2);
    }

    @DisplayName("잔액충전 테스트(잔액 충전, 충전 이력 저장)")
    @Test
    void chargeBalanceAndRecordHistory() {
        Long userId = 2L;
        Long amount = 5000L;

        balanceFrontService.completeCharge(new CompleteChargeParam(userId, amount));

        Balance balance = balanceLocalDatabase.findByUserId(userId).orElseThrow();
        assertEquals(amount, balance.getAmount());

        List<BalanceHistory> historyList = balanceHistoryLocalDatabase.findAll();
        assertEquals(1, historyList.size());

        var history = historyList.get(0);
        assertEquals(userId, history.getUserId());
        assertEquals(amount, history.getAmount());
    }

    @DisplayName("주문 생성 통합 테스트(중복 검증 → 재고 차감 → 금액 계산 → 주문 저장)")
    @Test
    void completeOrderIntegrationTest() {
        // given
        Long userId = 1L;
        Long expectedAmount = 1_020_000L;
        Long expectedOrderId = 1L;

        List<OrderItemParam> items = List.of(
                new OrderItemParam(1L, 1L),
                new OrderItemParam(2L, 2L)
        );
        OrderParam param = new OrderParam(userId, items);

        //then
        ResCompleteOrderDto result = orderFrontService.completeOrder(param);

        // todo 총액이 정상적으로 계산되었는지
        List<ReqCalculateTotalAmountDto> reqList = param.getItems().stream()
                .map(item -> new ReqCalculateTotalAmountDto(item.getProductId(), item.getQuantity()))
                .toList();
        long totalExpectedAmount = productService.calculateTotalAmount(reqList);

        // todo 주문이 실제로 저장됐는지 검증
        Order savedOrder = orderService.getOrderById(result.getOrderId());
        Assertions.assertEquals(expectedOrderId, savedOrder.getOrderId());
        Assertions.assertEquals(expectedAmount, savedOrder.getTotalAmount());
        Assertions.assertEquals(2, savedOrder.getItems().size());
        Assertions.assertEquals(expectedAmount, savedOrder.getTotalAmount());

        // todo 재고가 차감됐는지 검증
        for (OrderItemParam item : items) {
            Long productId = item.getProductId();
            Long orderedQty = item.getQuantity();
            Product product = productLocalDatabase.findByProductId(productId).orElseThrow();
            Assertions.assertEquals(10-orderedQty, product.getProductQuantity());
        }

        // todo result값 검증
        Assertions.assertNotNull(result);
        Assertions.assertEquals("CREATED", result.getStatus());
        assertEquals(expectedAmount, result.getTotalAmount());
        assertEquals(expectedOrderId, result.getOrderId());
    }

    @DisplayName("주문 → 결제 → 잔액 차감 및 이력 저장까지 전체 플로우 검증")
    @Test
    void paymentCompleteTest() {
        // todo 주문
        Long paramUserId = 1L;
        OrderParam orderParam = new OrderParam(paramUserId, List.of(
                new OrderItemParam(1L, 1L),
                new OrderItemParam(2L, 2L)
        ));
        ResCompleteOrderDto resCompleteOrderDto = orderFrontService.completeOrder(orderParam);
        Long paramOrderId =  resCompleteOrderDto.getOrderId();
        List<OrderItem> orderItems = resCompleteOrderDto.getItems();

        // todo 결제
        paymentFrontService.completePayment(new PaymentParam(paramUserId, paramOrderId));

        // todo 주문에 해당하는 상품 가져와지는지
        Order resOrder =  orderLocalDatabase.getOrderById(paramOrderId).orElseThrow();

        // todo 총 결제 금액 계산
        List<ReqCalculateTotalAmountDto> reqCalculateTotalAmountDtos = resCompleteOrderDto.getItems().stream()
                .map(item -> new ReqCalculateTotalAmountDto(item.getProductId(), item.getQuantity()))
                .toList();

        long totalAmount = productService.calculateTotalAmount(reqCalculateTotalAmountDtos);

        // todo 사용자 잔액 검증
        Balance balance = balanceLocalDatabase.findByUserId(paramOrderId).orElseThrow();
        assertEquals(100_000_000L - totalAmount, balance.getAmount());

        // todo 결제 내역 검증
        var payment = paymentHistoryLocalDatabase.findAll().get(0);
        assertEquals(paramUserId, payment.getUserId());
        assertEquals(-totalAmount, payment.getAmount());

        // todo 잔액 사용 이력 검증
        var balanceHistory = balanceHistoryLocalDatabase.findAll().get(0);
        assertEquals(paramUserId, balanceHistory.getUserId());
        assertEquals(-totalAmount, balanceHistory.getAmount());
    }



}
