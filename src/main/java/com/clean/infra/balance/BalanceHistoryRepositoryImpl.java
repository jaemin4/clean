package com.clean.infra.balance;

import com.clean.domain.balance.BalanceHistory;
import com.clean.domain.balance.BalanceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BalanceHistoryRepositoryImpl implements BalanceHistoryRepository {

    private final BalanceHistoryLocalDatabase balanceHistoryLocalDatabase;

    @Override
    public void save(BalanceHistory balanceHistory) {
        balanceHistoryLocalDatabase.save(balanceHistory);
    }
}
