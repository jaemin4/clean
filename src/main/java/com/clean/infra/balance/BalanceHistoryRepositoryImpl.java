package com.clean.infra.balance;

import com.clean.domain.balance.BalanceHistory;
import com.clean.domain.balance.BalanceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class BalanceHistoryRepositoryImpl implements BalanceHistoryRepository {

    private final BalanceHistoryJpaRepository balanceHistoryJpaRepository;

    @Override
    public void save(BalanceHistory balanceHistory) {
        balanceHistoryJpaRepository.save(balanceHistory);
    }
}
