package com.gold_mining_app_backend.dto;

import java.text.DecimalFormat;
import com.gold_mining_app_backend.enums.ProductCategory;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.modal.Product;
import com.gold_mining_app_backend.util.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
private String id;
private String name;
private ProductCategory category;
private ProductQuality quality;
private String price;
private String timeStamp;
private MiningSiteDTO miningSite;
public ProductDTO(Product product){
    if(product.getId()!=null)
    this.id=product.getId().toString();
    this.name=product.getName();
    this.category=product.getCategory();
    this.quality=product.getQuality();
    this.miningSite=new MiningSiteDTO(product.getMiningSite());
    DecimalFormat df=new DecimalFormat("#,###.#");
    this.price=df.format(product.getPrice());
    this.timeStamp=LocalDateTimeConverter.convertLocalDateTime(product.getTimeStamp(), "dd,MMM-yyyy");
}
}
