package com.gold_mining_app_backend.modal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.gold_mining_app_backend.enums.ProductCategory;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.input.ProductInput;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
private String name;
@Enumerated(EnumType.STRING)
private ProductCategory category;
@Enumerated(EnumType.STRING)
private ProductQuality quality;
private double price;
@ManyToOne(cascade =CascadeType.ALL,targetEntity = MiningSite.class )
private MiningSite miningSite;
private LocalDateTime timeStamp;
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "product",targetEntity = Inventory.class)
public List<Inventory>inventories;
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "product",targetEntity = Orders.class)
public List<Orders>orderList;
public Product(ProductInput input,MiningSite site){
    if(input.getId().length()!=0)this.id=UUID.fromString(input.getId());
    if(input.getName().length()==0)throw new RuntimeException("Gold name is required");
    this.name=input.getName();
    if(input.getCategory().name().length()==0)throw new RuntimeException("Gold Category is required");
    this.category=input.getCategory();
    if(input.getQuality().name().length()==0)throw new RuntimeException("Gold Quality is required");
    this.quality=input.getQuality();
    if(input.getPrice()<=0)throw new RuntimeException("Gold Price is required");
    this.price=input.getPrice();
    if(site==null)throw new RuntimeException("Gold site is required");
    this.miningSite=site;
    this.timeStamp=LocalDateTime.now();
}
}
