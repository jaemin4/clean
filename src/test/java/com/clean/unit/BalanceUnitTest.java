package com.clean.unit;

import com.clean.domain.balance.Balance;
import com.clean.domain.balance.BalanceHistoryRepository;
import com.clean.domain.balance.BalanceRepository;
import com.clean.domain.balance.BalanceService;
import com.clean.interfaces.model.dto.req.ReqChargeBalanceDto;
import com.clean.interfaces.model.dto.req.ReqUseBalanceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BalanceUnitTest {

    private BalanceService balanceService;
    private BalanceRepository balanceRepository;

    @BeforeEach
    void setUp() {
        balanceRepository = mock(BalanceRepository.class);
        BalanceHistoryRepository balanceHistoryRepository = mock(BalanceHistoryRepository.class);

        balanceService = new BalanceService(balanceRepository, balanceHistoryRepository);
    }

    @Test
    @DisplayName("잔액 테이블에 요청 유저가 존재하지 않으면 예외 발생")
    void notFoundBalance() {
        Long userId = 5L;
        when(balanceRepository.findByUserId(userId)).thenReturn(Optional.empty());

        ReqUseBalanceDto dto = new ReqUseBalanceDto(userId, 10_000L);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            balanceService.usableBalance(dto);
        });

        assertEquals("5 : 해당 유저가 존재하지 않습니다", ex.getMessage());
    }

    @Test
    @DisplayName("요청 금액보다 잔액이 부족하면 예외 발생")
    void notEnoughBalance() {
        Long userId = 1L;
        Balance balance = new Balance(userId, 10_000L);
        when(balanceRepository.findByUserId(userId)).thenReturn(Optional.of(balance));

        ReqUseBalanceDto dto = new ReqUseBalanceDto(userId, 100_000L);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            balanceService.usableBalance(dto);
        });

        assertEquals("잔액이 부족합니다.", ex.getMessage());
    }

    @Test
    @DisplayName("잔액이 정상적으로 충전된다")
    void chargeBalance() {
        Long userId = 2L;
        Balance balance = new Balance(userId, 0L);

        when(balanceRepository.findByUserId(userId)).thenReturn(Optional.of(balance));
        when(balanceRepository.updateBalance(any(Balance.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ReqChargeBalanceDto dto = new ReqChargeBalanceDto(userId, 5_000L);
        Balance result = balanceService.chargeableBalance(dto);

        assertEquals(5_000L, result.getAmount());
    }

    @Test
    @DisplayName("잔액이 정상적으로 사용된다")
    void useBalance() {
        Long userId = 3L;
        Balance balance = new Balance(userId, 5_000L);

        when(balanceRepository.findByUserId(userId)).thenReturn(Optional.of(balance));
        when(balanceRepository.updateBalance(any(Balance.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ReqUseBalanceDto dto = new ReqUseBalanceDto(userId, 5_000L);
        Balance result = balanceService.usableBalance(dto);

        assertEquals(0, result.getAmount());
    }
}
