package com.clean.domain.balance;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface BalanceHistoryRepository {
    void save (final BalanceHistory balanceHistory);
}
