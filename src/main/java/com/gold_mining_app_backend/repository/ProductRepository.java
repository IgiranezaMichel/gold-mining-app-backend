package com.gold_mining_app_backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gold_mining_app_backend.enums.ProductCategory;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.modal.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,UUID> {
    Optional<Product> findByQualityAndCategory(ProductQuality quality, ProductCategory category);

    Page<Product> findAllByName(String search, PageRequest of);

    Page<Product> findAllByNameContainingIgnoreCase(String search, PageRequest of);
}
