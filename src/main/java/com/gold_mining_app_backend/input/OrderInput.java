package com.gold_mining_app_backend.input;

import java.time.LocalDateTime;
import com.gold_mining_app_backend.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInput {
    private String id;
    private OrderStatus status;
    private double quantity;
    private LocalDateTime deadline;
    private String productId;
    private String userId;
}
