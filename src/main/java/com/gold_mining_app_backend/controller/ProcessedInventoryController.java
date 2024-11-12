package com.gold_mining_app_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.dto.ProcessedInventoryDTO;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.input.ProcessedInventoryInput;
import com.gold_mining_app_backend.services.ProcessedInventoryServices;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/processed-inventory")
public class ProcessedInventoryController {
   @Autowired
    private ProcessedInventoryServices pInventoryServices;

    @PostMapping("add-or-update-product")
    public ResponseEntity<String> createProcessedInventory(@RequestBody ProcessedInventoryInput processedInventoryInput) {
        return pInventoryServices.createProcessedInventory(processedInventoryInput);
    }
    @PostMapping("delete-product/{id}")
    public ResponseEntity<String> removeProcessedInventory(@PathVariable String  id) {
        return pInventoryServices.removeProcessedInventory(id);
    }
    @GetMapping("find-all")
    public PageDTO<ProcessedInventoryDTO> getAllProductPageList(@RequestBody PageInput pageInput) {
        return pInventoryServices.getAllProcessedInventory(pageInput);
    }
}
