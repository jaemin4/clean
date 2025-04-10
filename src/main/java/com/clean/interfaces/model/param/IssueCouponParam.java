package com.clean.interfaces.model.param;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueCouponParam {

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long couponId;
}
