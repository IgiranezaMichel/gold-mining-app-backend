package com.gold_mining_app_backend.modal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.gold_mining_app_backend.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
private UUID id;
private OrderStatus status;
private double quantity;
private LocalDateTime deadline;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Product.class)
private Product product;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = User.class)
private User user;
private LocalDateTime timeStamp;
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "order",targetEntity = Sales.class)
public List<Sales>salesList;
}
