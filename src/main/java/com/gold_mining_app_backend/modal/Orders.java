package com.gold_mining_app_backend.modal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.gold_mining_app_backend.enums.OrderStatus;
import com.gold_mining_app_backend.input.OrderInput;

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
public class Orders {
    @Id
    @UuidGenerator(style = Style.AUTO)
private UUID id;
private OrderStatus status;
private double quantity;
private LocalDateTime deadline;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Product.class)
private Product product;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = User.class)
private User user;
private LocalDateTime timeStamp;
public Orders(OrderInput orderInput,User usr,Product product){
    if(!orderInput.getId().isEmpty()){
        this.setId(UUID.fromString(orderInput.getId()));
    }
    if(orderInput.getStatus().name().isEmpty()){
        this.setStatus(OrderStatus.PENDING);
    }else{
    this.status=orderInput.getStatus();}
    if(this.deadline.isAfter(LocalDateTime.now()))throw new IllegalArgumentException("Order cannot be in the past");
    this.deadline=orderInput.getDeadline();
    if(product==null)throw new IllegalArgumentException("Product is required");
    this.product=product;
    if(usr==null)throw new IllegalArgumentException("User is required");
    this.user=usr;
}
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "order",targetEntity = Sales.class)
public List<Sales>salesList;
}
