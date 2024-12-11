package com.gold_mining_app_backend.modal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.gold_mining_app_backend.enums.MiningSiteStatus;
import com.gold_mining_app_backend.input.MiningSiteInput;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MiningSite {
    @UuidGenerator(style = Style.AUTO)
    @Id
    private UUID id;
    private String name;
    private String address;
    private MiningSiteStatus status;
     private LocalDateTime timeStamp;
     @OneToMany(cascade =CascadeType.ALL,mappedBy = "miningSite",targetEntity = Product.class)
     private List<Product>products;
    public MiningSite(MiningSiteInput miningSiteInput) {
        if(!miningSiteInput.getId().equals("")) 
        this.id = UUID.fromString(miningSiteInput.getId());
        if(miningSiteInput.getName().equals(""))throw new IllegalArgumentException("Name cannot be empty");
        this.name = miningSiteInput.getName();
        if(miningSiteInput.getAddress().equals(""))throw new IllegalArgumentException("Address cannot be empty");
        this.address = miningSiteInput.getAddress();
        if(miningSiteInput.getStatus() == null)throw new IllegalArgumentException("Status cannot be empty");
        this.status = miningSiteInput.getStatus();
        this.timeStamp = LocalDateTime.now();
    }
}
