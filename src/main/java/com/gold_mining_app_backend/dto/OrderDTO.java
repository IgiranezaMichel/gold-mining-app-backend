package com.gold_mining_app_backend.dto;

import java.time.LocalDateTime;
import com.gold_mining_app_backend.enums.OrderStatus;
import com.gold_mining_app_backend.modal.Orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
private String id;
private OrderStatus status;
private double quantity;
private LocalDateTime deadline;
private ProductDTO product;
private UserDTO user;
private LocalDateTime timeStamp;
public OrderDTO(Orders order) {
    if(order.getId()!=null)
    this.id = order.getId().toString();
    this.status = order.getStatus();
    this.quantity = order.getQuantity();
    this.deadline = order.getDeadline();
    this.product = new ProductDTO(order.getProduct());
    this.user = new UserDTO(order.getUser());
    this.timeStamp = order.getTimeStamp();
}
}
