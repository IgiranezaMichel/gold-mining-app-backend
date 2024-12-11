package com.gold_mining_app_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.gold_mining_app_backend.modal.*;
public interface MiningSiteRepository extends JpaRepository<MiningSite, UUID> {

}
