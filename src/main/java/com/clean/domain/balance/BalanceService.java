package com.clean.domain.balance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceHistoryRepository balanceHistoryRepository;

    public void charge(BalanceCommand.Charge command) {
        Balance balance = balanceRepository.findByUserId(command.getUserId())
                .map(b -> {
                    b.charge(command.getAmount());
                    return b;
                })
                .orElseGet(() -> {
                    Balance newBalance = Balance.create(command.getUserId(), command.getAmount());
                    return balanceRepository.save(newBalance);
                });

        balanceHistoryRepository.save(
                BalanceHistory.charge(balance.getBalanceId(), command.getAmount())
        );
    }


    public void use(BalanceCommand.Use command) {
        Balance balance = balanceRepository.findByUserId(command.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 잔액 정보가 없습니다."));

        balance.use(command.getAmount());

        balanceHistoryRepository.save(
                BalanceHistory.use(balance.getBalanceId(), command.getAmount())
        );
    }


}
