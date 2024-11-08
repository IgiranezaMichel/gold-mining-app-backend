package com.gold_mining_app_backend.dto;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.UUID;

import com.gold_mining_app_backend.modal.Inventory;
import com.gold_mining_app_backend.modal.ProcessedInventory;
import com.gold_mining_app_backend.modal.Product;
import com.gold_mining_app_backend.util.LocalDateTimeConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessedInventoryDTO {
private String id;
private InventoryDTO inventory;
private String qtyOfUnprocesed;
private String qtyOfprocesed;
private String timeStamp;
public ProcessedInventoryDTO(ProcessedInventory processedInventoryDTO) {
    this.id = processedInventoryDTO.getId().toString();
    DecimalFormat df = new DecimalFormat("#,###.##");
    this.qtyOfprocesed = df.format(processedInventoryDTO.getQtyOfprocesed());
    this.qtyOfUnprocesed = df.format(processedInventoryDTO.getQtyOfUnprocesed());
    this.timeStamp = LocalDateTimeConverter.convertLocalDateTime(processedInventoryDTO.getTimeStamp(), "dd,MMM-yyyy HH:mm:ss a") ;
    this.inventory = new InventoryDTO(processedInventoryDTO.getInventory());

}
}
