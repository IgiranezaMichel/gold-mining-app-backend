package com.gold_mining_app_backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gold_mining_app_backend.modal.ProcessedInventory;
@Repository
public interface ProcessedInventoryRepository extends JpaRepository<ProcessedInventory,UUID> {
    Optional<ProcessedInventory> findTopByOrderByIdDesc();

}
