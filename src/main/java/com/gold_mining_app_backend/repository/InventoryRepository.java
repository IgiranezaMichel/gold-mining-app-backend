package com.gold_mining_app_backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.modal.Inventory;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,UUID>{

    Page<Inventory> findAllByProductQuality(PageRequest of, ProductQuality quality);

}
