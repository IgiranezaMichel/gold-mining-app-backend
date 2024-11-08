package com.gold_mining_app_backend.input;

import java.time.LocalDateTime;
import com.gold_mining_app_backend.enums.SalesMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesInput {
    private String id;
    private LocalDateTime timeStamp;
    private String inventoryId;
    private String orderId;
    private SalesMethod method;
}
