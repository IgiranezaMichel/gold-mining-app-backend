package com.gold_mining_app_backend.input;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessedInventoryInput {
private String id;
private String inventoryId;
private double qtyOfUnprocesed;
private double qtyOfprocesed;
private LocalDateTime timeStamp;
} 