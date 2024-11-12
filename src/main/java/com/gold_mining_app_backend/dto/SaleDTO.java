package com.gold_mining_app_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import com.gold_mining_app_backend.enums.SalesMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleDTO {
private UUID id;
    private LocalDateTime timeStamp;
    private InventoryDTO inventory;
    private OrderDTO order;
    private SalesMethod method;
}
