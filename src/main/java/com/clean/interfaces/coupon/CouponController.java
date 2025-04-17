package com.clean.interfaces.coupon;

import com.clean.domain.coupon.CouponService;
import com.clean.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping(value = "/issue")
    public ApiResponse<Void> issueCoupon(@RequestBody CouponRequest.Issue request) {
        couponService.issue(request.toCommand());
        return ApiResponse.success();
    }

}
