package com.gold_mining_app_backend.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gold_mining_app_backend.enums.ProductCategory;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.modal.Product;

public interface ProductRepository extends JpaRepository<Product,UUID> {
    Optional<Product> findByQualityAndCategory(ProductQuality quality, ProductCategory category);
}
