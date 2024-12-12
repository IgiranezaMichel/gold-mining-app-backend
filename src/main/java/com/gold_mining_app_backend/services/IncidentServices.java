package com.gold_mining_app_backend.services;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gold_mining_app_backend.dto.IncidentDTO;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.input.IncidentInput;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.modal.Incident;
import com.gold_mining_app_backend.modal.User;
import com.gold_mining_app_backend.repository.IncidentRepository;
import com.gold_mining_app_backend.repository.UserRepository;

@Service
public class IncidentServices {
    @Autowired
    IncidentRepository incidentRepository;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> createIncident(IncidentInput incidentInput, Principal principal) {
        try {
            User user = userRepository.findByEmail(principal.getName())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Incident incident = new Incident(incidentInput, user);
            incidentRepository.save(incident);
            return ResponseEntity.ok("Incident created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public PageDTO<IncidentDTO> getIncidentItemsList(PageInput pageInput) {
        if (pageInput.getSearch().length() == 0) {
            
        }
        if (pageInput.getSearch()==null) {
            Page<Incident> page = incidentRepository.findAll(
                    PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())));
            return new PageDTO<>(page.getNumber(), (int) page.getTotalPages(), page.getSize(),
                    page.getContent().stream().map(IncidentDTO::new).toList());
        }
        Page<Incident> page = incidentRepository
                .findAllByUserPostedNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    pageInput.getSearch(),pageInput.getSearch(),
                        PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(),
                                Sort.by(pageInput.getSortBy()))
                                );
        return new PageDTO<>(page.getNumber(), (int) page.getTotalPages(), page.getSize(),
                page.getContent().stream().map(IncidentDTO::new).toList());
    }
}
