package com.clean.infra.balance;

import com.clean.domain.balance.BalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface BalanceHistoryJpaRepository extends JpaRepository<BalanceHistory, Long> {


}
