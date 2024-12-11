package com.gold_mining_app_backend.repository;

import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gold_mining_app_backend.dto.InventoryDetailDTO;
import com.gold_mining_app_backend.enums.ProductCategory;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.modal.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    Page<Inventory> findAllByProductQuality(PageRequest of, ProductQuality quality);

    Optional<Inventory> findTopByOrderByTimeStampDesc();

    Optional<Inventory> findFirstByProductIdOrderByTimeStampDesc(UUID id);

    Optional<Inventory> findFirstByProductQualityAndProductCategoryOrderByTimeStampDesc(ProductQuality quality,
            ProductCategory category);
    @Query("""
            SELECT
            new com.gold_mining_app_backend.dto.InventoryDetailDTO(
            p.name,p.category,p.quality,p.price,m.name,m.address,m.status,i.id,i.qtyInStock,i.timeStamp) 
            FROM Inventory i RIGHT JOIN Product p  on i.product.id=p.id INNER JOIN MiningSite m on p.miningSite.id=m.id
         group by i.id,p.id,m.id,p.name,p.category,p.quality,p.miningSite.id order by i.timeStamp desc

            """)
    List<InventoryDetailDTO> findAllAvailableInventory();
}
