package com.gold_mining_app_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gold_mining_app_backend.modal.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders,UUID>{

}
