package com.gold_mining_app_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.gold_mining_app_backend.dto.InventoryDetailDTO;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.modal.Inventory;
import com.gold_mining_app_backend.services.InventoryServices;
import com.gold_mining_app_backend.input.InventoryInput;

@RestController
@CrossOrigin
@RequestMapping("/api/inventory")
public class InventoryController {
  @Autowired
    private InventoryServices inventoryServices;

    @PostMapping("create/{action}")
    public ResponseEntity<String> createInventory(@RequestBody InventoryInput InventoryInput,@PathVariable String action) {
        return inventoryServices.createInventory(InventoryInput,action);
    }
    @PostMapping("delete-inventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable String  id) {
        return inventoryServices.deleteInventory(id);
    }
    @GetMapping("get/detail/{inventoryId}")
    public InventoryDTO getInventoryDetail(@PathVariable String inventoryId) {
        return inventoryServices.getInventoryDetailPrinciple(inventoryId);
    }
    @GetMapping("find-all")
    public List<InventoryDetailDTO> getAllProductInventoryPageList() {
        return inventoryServices.getAllInventoryPageList();
    }
    // @GetMapping("find-all/Inventory/{productId}")
    // public PageDTO<InventoryDTO> getAllProductInventoryPageList(@RequestBody PageInput pageInput, @RequestParam ProductQuality productQuality) {
    //     return inventoryServices.getAllInventoryPageList(pageInput, productQuality);
    // }
}
