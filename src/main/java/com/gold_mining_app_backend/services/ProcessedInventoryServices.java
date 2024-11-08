package com.gold_mining_app_backend.services;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.dto.ProcessedInventoryDTO;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.input.ProcessedInventoryInput;
import com.gold_mining_app_backend.modal.Inventory;
import com.gold_mining_app_backend.modal.ProcessedInventory;
import com.gold_mining_app_backend.repository.InventoryRepository;
import com.gold_mining_app_backend.repository.ProcessedInventoryRepository;

@Service
public class ProcessedInventoryServices {
@Autowired private ProcessedInventoryRepository processedInventoryRepository;
@Autowired private InventoryRepository inventoryRepository;

public ResponseEntity<String> createProcessedInventory(ProcessedInventoryInput processedInventoryInput) {
   try {
    Inventory inventory = inventoryRepository.findById(UUID.fromString(processedInventoryInput.getInventoryId())).orElseThrow(()->new RuntimeException("Please specify Inventory"));
    processedInventoryRepository.save(new ProcessedInventory(processedInventoryInput,inventory));
    return ResponseEntity.ok("Processed Inventory created successfully");
   } catch (Exception e) {
    return ResponseEntity.badRequest().body(e.getMessage());
   }
}
public ProcessedInventory findLastRow(){
    return processedInventoryRepository.findTopByOrderByIdDesc().stream().findFirst().orElse(null);
}
public ResponseEntity<String> removeProcessedInventory(String id) {
   try {
    processedInventoryRepository.deleteById(UUID.fromString(id));
    return ResponseEntity.ok("Processed Inventory deleted successfully");
   } catch (Exception e) {
    return ResponseEntity.badRequest().body(e.getMessage());
   }
}

public PageDTO<ProcessedInventoryDTO> getAllProcessedInventory(PageInput pageInput) {
 Page<com.gold_mining_app_backend.modal.ProcessedInventory> page = processedInventoryRepository.findAll(
                PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())));
 return new PageDTO<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                page.getContent().stream().map(ProcessedInventoryDTO::new).toList());
            }
}
