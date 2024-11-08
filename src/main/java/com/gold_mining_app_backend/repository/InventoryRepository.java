package com.gold_mining_app_backend.repository;

import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gold_mining_app_backend.enums.ProductCategory;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.modal.Inventory;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,UUID>{

    Page<Inventory> findAllByProductQuality(PageRequest of, ProductQuality quality);

    Optional<Inventory> findTopByOrderByTimeStampDesc();

    Optional<Inventory> findFirstByProductQualityAndProductCategoryOrderByTimeStampDesc(ProductQuality quality,
            ProductCategory category);

}
