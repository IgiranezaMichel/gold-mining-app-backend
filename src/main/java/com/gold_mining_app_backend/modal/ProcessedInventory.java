package com.gold_mining_app_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import com.gold_mining_app_backend.input.ProcessedInventoryInput;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessedInventory {
 @UuidGenerator(style = Style.AUTO)
 @Id
private UUID id;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Inventory.class)
private Inventory inventory;
private double qtyOfUnprocesed;
private double qtyOfprocesed;
private LocalDateTime timeStamp;
public ProcessedInventory(ProcessedInventoryInput inv,Inventory inventory){
    if(!inv.getId().isEmpty())
    this.id=UUID.fromString(inv.getId());
    if(inventory==null)throw new RuntimeException("Inventory is null");
    this.inventory=inventory;
    if(inv.getQtyOfUnprocesed()<0)throw new RuntimeException("Qty of unprocessed is negative");
    this.qtyOfUnprocesed=inv.getQtyOfUnprocesed();
    if(inv.getQtyOfprocesed()<0)throw new RuntimeException("Qty of processed is negative");
    this.qtyOfprocesed=inv.getQtyOfprocesed();
    this.timeStamp=LocalDateTime.now();
}
}
