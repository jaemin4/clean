package com.clean.interfaces.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResIssueCouponDto {
    private String couponTitle;
    private String discountType;
    private Integer discountValue;

}
