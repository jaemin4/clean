package com.clean.infra.balance;

import com.clean.domain.balance.Balance;
import com.clean.domain.balance.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class BalanceRepositoryImpl implements BalanceRepository {

    private final BalanceJpaRepository balanceJpaRepository;

    @Override
    public Balance save(Balance balance) {
        return balanceJpaRepository.save(balance);
    }

    @Override
    public Optional<Balance> findByUserId(Long userId) {
        return balanceJpaRepository.findByUserId(userId);
    }
}
