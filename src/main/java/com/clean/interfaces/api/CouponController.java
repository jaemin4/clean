package com.clean.interfaces.api;

import com.clean.domain.coupon.CouponService;
import com.clean.interfaces.model.param.IssueCouponParam;
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
    public void issueCoupon(@RequestBody IssueCouponParam param) {
        couponService.issueCoupon(param);
    }

}
