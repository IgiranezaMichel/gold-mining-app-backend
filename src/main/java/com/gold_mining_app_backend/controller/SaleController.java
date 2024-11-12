package com.gold_mining_app_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.dto.SaleDTO;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.input.SalesInput;
import com.gold_mining_app_backend.services.SaleServices;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired private SaleServices saleServices;
@PostMapping("client/add-or-update")
public ResponseEntity<String> createClientSale(@RequestBody SalesInput entity) {
  return saleServices.createClientSale(entity);
}
@GetMapping("client/find-all")
public PageDTO<SaleDTO> getAllClientSale(@RequestBody PageInput pageInput,Principal principal) {
  return saleServices.getAllClientSale(pageInput);
}
}
