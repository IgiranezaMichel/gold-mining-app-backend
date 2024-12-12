package com.gold_mining_app_backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gold_mining_app_backend.modal.Incident;
@Repository
public interface IncidentRepository extends JpaRepository<Incident, UUID> {

    Page<Incident> findAllByUserPostedNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String search,String _search,PageRequest of);

}
