package com.clean.infra.balance;

import com.clean.domain.balance.BalanceHistory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class BalanceHistoryLocalDatabase {

    private Map<Long, BalanceHistory> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public void save(BalanceHistory balanceHistory) {
        Long id = idGenerator.incrementAndGet();
        balanceHistory.setBalanceHistoryId(id);
        localDb.put(id,balanceHistory);
    }

    public List<BalanceHistory> findAll() {
        return new ArrayList<>(localDb.values());
    }
}
