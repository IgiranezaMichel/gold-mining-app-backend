package com.gold_mining_app_backend.input;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryInput {
private String id;
private double qtyInStock;
private LocalDateTime timeStamp;
private String productId;
}
