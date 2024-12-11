package com.gold_mining_app_backend.dto;

import com.gold_mining_app_backend.modal.Inventory;
import com.gold_mining_app_backend.util.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryDTO {
    private String id;
    private double qtyInStrock;
    private String timeStamp;
    private ProductDTO product;

    public InventoryDTO(Inventory inventory) {
        if(inventory.getId()!=null)
        this.id = inventory.getId().toString();
        this.qtyInStrock = inventory.getQtyInStock();
        this.timeStamp = LocalDateTimeConverter.convertLocalDateTime(inventory.getTimeStamp(), "dd, MMM-yyy HH:mm a");
        this.product = new ProductDTO(inventory.getProduct());
    }
}
