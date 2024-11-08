package com.gold_mining_app_backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gold_mining_app_backend.enums.OrderStatus;
import com.gold_mining_app_backend.modal.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders,UUID>{
    Page<Orders> findAllByStatus(PageRequest of, OrderStatus orderStatus);
    Page<Orders> findAllByUserEmailAndStatus(PageRequest of,String email, OrderStatus orderStatus);

}
