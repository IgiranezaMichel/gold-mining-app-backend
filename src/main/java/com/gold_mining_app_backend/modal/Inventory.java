package com.gold_mining_app_backend.modal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.gold_mining_app_backend.input.InventoryInput;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inventory {
@UuidGenerator(style = Style.AUTO)
@Id
private UUID id;
private double qtyInStock;
private LocalDateTime timeStamp;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Product.class)
private Product product;
public Inventory(InventoryInput in,Product product){
    if(!in.getId().equals(""))this.id=UUID.fromString(in.getId());
    if(product==null)throw new RuntimeException("Product is required");
    this.product=product;
    if(in.getQtyInStock()<0||in.getQtyInStock()==0)throw new RuntimeException("Quantity must be greater than 0");
    this.qtyInStock=in.getQtyInStock();
    this.timeStamp=LocalDateTime.now();
}
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "inventory",targetEntity = Sales.class)
public List<Sales>salesList;
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "inventory",targetEntity = ProcessedInventory.class)
public List<Sales>processedInventoryList;

}
