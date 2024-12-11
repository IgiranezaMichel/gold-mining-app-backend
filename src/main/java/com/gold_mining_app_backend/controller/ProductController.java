package com.gold_mining_app_backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.dto.ProductDTO;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.input.ProductInput;
import com.gold_mining_app_backend.services.ProductServices;
@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductServices userServices;

    @PostMapping("create")
    public ResponseEntity<String> createProduct(@RequestBody ProductInput product) {
        return userServices.createPrduct(product);
    }
    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String  id) {
        return userServices.deleteProduct(id);
    }
    @PostMapping("find-all")
    public PageDTO<ProductDTO> getAllProductPageList(@RequestBody PageInput pageInput) {
        return userServices.getAllProduct(pageInput);
    }
    @GetMapping("get-all")
    public List<ProductDTO> getAllProduct() {
        return userServices.getAllProduct();
    }
}
