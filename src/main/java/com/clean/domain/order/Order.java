package com.clean.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Order {
    private Long orderId;
    private Long userId;
    private List<OrderItem> items;
    private Long totalAmount;
    private String status;
    private LocalDateTime createAt;
}
