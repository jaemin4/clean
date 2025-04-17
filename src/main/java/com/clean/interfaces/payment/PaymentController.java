//package com.clean.interfaces.payment;
//
//import com.clean.application.payment.PaymentFacade;
//import com.clean.support.ApiResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/payments")
//@RequiredArgsConstructor
//public class PaymentController {
//    private final PaymentFacade paymentFacade;
//
//    @PostMapping(value = "/pay")
//    public ApiResponse<Void> payment(PaymentRequest.Payment request) {
//        paymentFacade.pay(request.toCriteria());
//
//        return ApiResponse.success();
//    }
//}
