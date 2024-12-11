package com.gold_mining_app_backend.services;

import com.gold_mining_app_backend.input.MiningSiteInput;
import com.gold_mining_app_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.gold_mining_app_backend.modal.MiningSite;
import com.gold_mining_app_backend.enums.MiningSiteStatus;
import java.util.*;
import com.gold_mining_app_backend.dto.MiningSiteDTO;
import org.springframework.stereotype.Service;
@Service
public class MiningSiteServices {
    @Autowired
    private MiningSiteRepository miningSiteRepository;

    public ResponseEntity<String> createMiningSite(MiningSiteInput miningSiteInput) {
        try {
            miningSiteRepository.save(new MiningSite(miningSiteInput));
            return ResponseEntity.status(HttpStatus.OK).body("Mining Site Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<String> updatingMiningSiteStatus(String id, MiningSiteStatus status) {
        try {
            MiningSite mining = miningSiteRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new IllegalArgumentException("Mining Site not found"));
            mining.setStatus(status);
            miningSiteRepository.save(mining);
            return ResponseEntity.status(HttpStatus.OK).body("Mining Site Status Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    public List<MiningSiteDTO> getAllMiningSites() {
      return miningSiteRepository.findAll().stream().map(MiningSiteDTO::new).toList();
    }
}
