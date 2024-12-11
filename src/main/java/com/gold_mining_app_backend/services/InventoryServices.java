package com.gold_mining_app_backend.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gold_mining_app_backend.dto.InventoryDTO;
import com.gold_mining_app_backend.dto.InventoryDetailDTO;
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
                .findFirstByProductQualityAndProductCategoryOrderByTimeStampDesc(product.getQuality(),
                        product.getCategory())
                .stream().findFirst().orElseThrow();
        if (action.equals("add")) {
            inventory.setQtyInStock(inventory.getQtyInStock() + qty);
        } else if (action.equals("remove")) {
            inventory.setQtyInStock(inventory.getQtyInStock() - qty);
        }
        inventory.setProduct(product);
        return inventory;
    }

    public ResponseEntity<String> createInventory(InventoryInput inventoryInput, String action) {
        try {
            Optional<Inventory> inventory = inventoryRepository
                    .findFirstByProductIdOrderByTimeStampDesc(UUID.fromString(inventoryInput.getProductId()));
            // add new inventory
            if (action.equals("add")) {
                if (!inventory.isPresent()) {
                    inventoryInput.setQtyInStock(inventory.get().getQtyInStock() + inventoryInput.getQtyInStock());
                    inventoryRepository.save(new Inventory(inventoryInput, inventory.get().getProduct()));
                    return new ResponseEntity<>("Quantity added in stock successful", HttpStatus.CREATED);
                } else {
                    inventoryRepository.save(new Inventory(inventoryInput, inventory.get().getProduct()));
                    return new ResponseEntity<>("Product added in stock", HttpStatus.CREATED);
                }
            } else if (!inventory.isPresent()) {
                return new ResponseEntity<>("Stock not found", HttpStatus.BAD_GATEWAY);
            } else {
                double removeItem = inventory.get().getQtyInStock() - inventoryInput.getQtyInStock();
                if (removeItem < 0)
                    throw new RuntimeException("Quantity must be less than stock");
                inventoryInput.setQtyInStock(removeItem);
                inventoryRepository.save(new Inventory(inventoryInput, inventory.get().getProduct()));
                return new ResponseEntity<>("Product is removed in stock successful", HttpStatus.CREATED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    public InventoryDTO getInventoryDetailPrinciple(String inventoryId) {
        return inventoryRepository.findById(UUID.fromString(inventoryId)).stream().map(InventoryDTO::new).findFirst()
                .orElse(null);
    }

    public List<InventoryDetailDTO> getAllInventoryPageList() {
        // Page<Inventory> page = inventoryRepository.findAllByProductQuality(
        //         PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())),
        //         quality);
        // return new PageDTO<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
        //         page.getContent().stream().map(InventoryDTO::new).toList());
        return inventoryRepository.findAllAvailableInventory();
    }

    // public InventoryDTO getAllInventory(PageInput pageInput) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getAllInventory'");
    // }
}
