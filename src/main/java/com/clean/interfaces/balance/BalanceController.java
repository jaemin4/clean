package com.clean.interfaces.balance;

import com.clean.domain.balance.BalanceService;
import com.clean.support.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @PostMapping("/charge")
    public ApiResponse<Void> charge(@Valid @RequestBody BalanceRequest.Charge request) {
        balanceService.charge(request.toCommand());
        return ApiResponse.success();
    }

}
