package com.gold_mining_app_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.gold_mining_app_backend.enums.SalesMethod;
import com.gold_mining_app_backend.input.SalesInput;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    @UuidGenerator(style = Style.AUTO)
    @Id
    private UUID id;
    private LocalDateTime timeStamp;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, targetEntity = Inventory.class)
    private Inventory inventory;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, targetEntity = Orders.class, optional = true)
    private Orders order;
    @Enumerated(EnumType.STRING)
    private SalesMethod method;
 
    public Sales(SalesInput salesInput, Inventory inventory, Orders order) {
        this.inventory = inventory;
        this.order = order;
        this.method = salesInput.getMethod();
        this.timeStamp = LocalDateTime.now();
    }
}
