package com.gold_mining_app_backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gold_mining_app_backend.input.SalesInput;
import com.gold_mining_app_backend.modal.Inventory;
import com.gold_mining_app_backend.modal.Orders;
import com.gold_mining_app_backend.modal.Sales;
import com.gold_mining_app_backend.repository.InventoryRepository;
import com.gold_mining_app_backend.repository.OrderRepository;
import com.gold_mining_app_backend.repository.SaleRepository;

@Service
public class SaleServices {
    @Autowired
    private SaleRepository salesRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<String> createClientSale(SalesInput entity) {
        try {
            Inventory inventory = inventoryRepository.findById(UUID.fromString(entity.getId()))
                    .orElseThrow(() -> new RuntimeException("Inventory not found"));
            Orders orders = orderRepository.findById(UUID.fromString(entity.getOrderId())).orElse(null);
            salesRepository.save(new Sales(entity, inventory, orders));
            return ResponseEntity.ok("Sale created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
