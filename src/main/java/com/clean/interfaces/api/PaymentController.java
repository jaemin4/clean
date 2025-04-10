package com.clean.interfaces.api;

import com.clean.application.PaymentFrontService;
import com.clean.interfaces.model.dto.res.ResCompletePaymentDto;
import com.clean.interfaces.model.param.PaymentParam;
import com.clean.interfaces.model.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentFrontService paymentFrontService;

    @PostMapping(value = "/pay")
    public ResponseEntity<ApiResponse<ResCompletePaymentDto>> completePayment(@RequestBody PaymentParam param) {
        ResCompletePaymentDto resDto = paymentFrontService.completePayment(param);
        ApiResponse<ResCompletePaymentDto> response = new ApiResponse<>("결제 완료", resDto);

        return ResponseEntity.ok(response);
    }
}
