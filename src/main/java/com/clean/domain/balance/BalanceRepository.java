package com.clean.domain.balance;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public interface BalanceRepository {
    Balance save(final Balance balance);
    Optional<Balance> findByUserId(final Long userId);
}
