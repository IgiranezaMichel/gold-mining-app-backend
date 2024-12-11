package com.gold_mining_app_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryDetailDTO {
    private Object productName;
    private Object productCategory;
    private Object productQuality;
    private Object productPrice;

    private Object productMiningSiteName;
    private Object productMiningSiteLocation;
    private Object productMiningSiteStatus;

    private Object inventoryId;
    private Object inventoryQtyInStock;
    private Object timeStamp;
}
