package com.gold_mining_app_backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gold_mining_app_backend.input.MiningSiteInput;
import com.gold_mining_app_backend.services.MiningSiteServices;
import com.gold_mining_app_backend.dto.MiningSiteDTO;
import java.util.List;
import com.gold_mining_app_backend.enums.MiningSiteStatus;
@RestController
@CrossOrigin
@RequestMapping("/api/mining-site")
public class MiningSiteController {
@Autowired private MiningSiteServices miningSiteServices;
@PostMapping("create")
public ResponseEntity<String> createMiningSite(@RequestBody MiningSiteInput miningSiteInput) {
  return miningSiteServices.createMiningSite(miningSiteInput);}
@PostMapping("update-status/{id}")
public ResponseEntity<String> updatingMiningSiteStatus(@PathVariable String id, @RequestParam MiningSiteStatus status) {
  return miningSiteServices.updatingMiningSiteStatus(id, status);
}
@GetMapping("get-all")
public List<MiningSiteDTO> getAllMiningSites() {
  return miningSiteServices.getAllMiningSites();
}
}
