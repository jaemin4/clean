package com.clean.interfaces.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqPaymentHistoryDto {
    private Long userId;
    private Long amount;
    private Long orderId;
    private String transactionId;
    private String status;
}
