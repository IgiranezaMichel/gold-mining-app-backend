package com.gold_mining_app_backend.input;
import com.gold_mining_app_backend.enums.ProductCategory;
import com.gold_mining_app_backend.enums.ProductQuality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInput {
private String id;
private String miningSiteId;
private String name;
private ProductCategory category;
private ProductQuality quality;
private double price;
}
