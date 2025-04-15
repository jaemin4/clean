package com.clean.application;

import com.clean.domain.balance.Balance;
import com.clean.domain.balance.BalanceService;
import com.clean.interfaces.model.dto.req.ReqChargeBalanceDto;
import com.clean.interfaces.model.dto.req.ReqRecordBalanceHistoryDto;
import com.clean.interfaces.model.dto.res.ResCompleteChargeDto;
import com.clean.interfaces.model.param.CompleteChargeParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceFrontService {
    
    private final BalanceService balanceService;
    
    public ResCompleteChargeDto completeCharge(CompleteChargeParam param) {
        Long reqUserId = param.getUserId();
        Long reqAmount = param.getAmount();

        // todo 잔액 충전
        Balance resBalance = balanceService.chargeableBalance(new ReqChargeBalanceDto(
                reqUserId, reqAmount)
        );
        
        // todo 잔액내역 저장
        balanceService.recordBalanceHistory(new ReqRecordBalanceHistoryDto(
                reqUserId, reqAmount)
        );
        
        return new ResCompleteChargeDto(reqUserId,reqAmount,resBalance.getAmount());
    }
    
}
