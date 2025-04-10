package com.clean;

import com.clean.application.BalanceFrontService;
import com.clean.domain.balance.Balance;
import com.clean.domain.balance.BalanceHistory;
import com.clean.domain.product.Product;
import com.clean.infra.balance.BalanceHistoryLocalDatabase;
import com.clean.infra.balance.BalanceLocalDatabase;
import com.clean.infra.product.ProductLocalDatabase;
import com.clean.interfaces.model.param.CompleteChargeParam;
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




}
