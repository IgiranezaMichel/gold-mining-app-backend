package com.gold_mining_app_backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gold_mining_app_backend.dto.InventoryDTO;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.input.InventoryInput;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.modal.Inventory;
import com.gold_mining_app_backend.modal.Product;
import com.gold_mining_app_backend.repository.InventoryRepository;
import com.gold_mining_app_backend.repository.ProductRepository;

@Service
public class InventoryServices {
    @Autowired
    private InventoryRepository inventoryRepository;
    private ProductRepository productRepository;

    public ResponseEntity<String> deleteInventory(String id) {
        try {
            Inventory inventory = inventoryRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new RuntimeException("Inventory doen't exist"));
            inventoryRepository.delete(inventory);
            return new ResponseEntity<>("Inventory deleted successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public Inventory manageInventory(String action, InventoryInput input, double qty) {
        Product product = productRepository.findById(UUID.fromString(input.getProductId()))
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Inventory inventory = inventoryRepository
                .findFirstByProductQualityAndProductCategoryOrderByTimeStampDesc(product.getQuality(), product.getCategory())
                .stream().findFirst().orElseThrow();
        if (action.equals("add")) {
            inventory.setQtyInStrock(inventory.getQtyInStrock() + qty);
        } else if (action.equals("remove")) {
            inventory.setQtyInStrock(inventory.getQtyInStrock() - qty);
        }
        inventory.setProduct(product);
        return inventory;
    }

    public ResponseEntity<String> createInventory(Inventory inventoryInput) {
        try {
            inventoryRepository.save(inventoryInput);
            return new ResponseEntity<>("Product added in inventory", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    public InventoryDTO getInventoryDetailPrinciple(String inventoryId) {
        return inventoryRepository.findById(UUID.fromString(inventoryId)).stream().map(InventoryDTO::new).findFirst()
                .orElse(null);
    }

    public PageDTO<InventoryDTO> getAllInventoryPageList(PageInput pageInput, ProductQuality quality) {
        Page<Inventory> page = inventoryRepository.findAllByProductQuality(
                PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())),
                quality);
        return new PageDTO<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                page.getContent().stream().map(InventoryDTO::new).toList());
    }
}
