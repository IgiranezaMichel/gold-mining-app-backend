package com.gold_mining_app_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gold_mining_app_backend.repository.InventoryRepository;

@Service
public class InventoryServices {
@Autowired private InventoryRepository inventoryRepository;
}
