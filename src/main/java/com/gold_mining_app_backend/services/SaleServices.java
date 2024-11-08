package com.gold_mining_app_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gold_mining_app_backend.input.SalesInput;
import com.gold_mining_app_backend.repository.SaleRepository;

@Service
public class SaleServices {
@Autowired private SaleRepository salesRepository;

public ResponseEntity<String> createClientSale(SalesInput entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createClientSale'");
}
}
