package com.gold_mining_app_backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.dto.InventoryDTO;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.modal.Inventory;
import com.gold_mining_app_backend.services.InventoryServices;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
  @Autowired
    private InventoryServices inventoryServices;

    @PostMapping("create-account")
    public ResponseEntity<String> createInventory(@RequestBody Inventory InventoryInput) {
        return inventoryServices.createInventory(InventoryInput);
    }
    @PostMapping("delete-inventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable String  id) {
        return inventoryServices.deleteInventory(id);
    }
    @GetMapping("get/detail/{inventoryId}")
    public InventoryDTO getInventoryDetail(@PathVariable String inventoryId) {
        return inventoryServices.getInventoryDetailPrinciple(inventoryId);
    }

    @GetMapping("find-all/Inventory/{productId}")
    public PageDTO<InventoryDTO> getAllProductInventoryPageList(@RequestBody PageInput pageInput, @RequestParam ProductQuality productQuality) {
        return inventoryServices.getAllInventoryPageList(pageInput, productQuality);
    }
}
