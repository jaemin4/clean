package com.clean.interfaces.model.dto.res;

import com.clean.domain.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResCompletePaymentDto {
    private Long orderId;
    private Long userId;
    private Long totalAmount;
    private List<OrderItem> orderItems;

}
