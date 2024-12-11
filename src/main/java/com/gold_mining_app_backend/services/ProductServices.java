package com.gold_mining_app_backend.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.dto.ProductDTO;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.input.ProductInput;
import com.gold_mining_app_backend.modal.MiningSite;
import com.gold_mining_app_backend.modal.Product;
import com.gold_mining_app_backend.repository.MiningSiteRepository;
import com.gold_mining_app_backend.repository.ProductRepository;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MiningSiteRepository miningSiteRepository;

    public ResponseEntity<String> createPrduct(ProductInput product) {
        try {
            /* Check existance of product in the store */
            // Product
            // dbProduct=productRepository.findByMiningSiteIdQualityAndCategory(UUID.fromString(product.getMiningSiteId()),product.getQuality(),product.getCategory()).orElse(null);
            // if(dbProduct!=null){
            // product.setId(dbProduct.getId().toString());
            // }
            MiningSite miningSite = miningSiteRepository.findById(UUID.fromString(product.getMiningSiteId()))
                    .orElseThrow(() -> new RuntimeException("Mining site not found"));
            productRepository.save(new Product(product, miningSite));
            // if(dbProduct==null)
            return new ResponseEntity<>("Product saved successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteProduct(String id) {
        try {
            productRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            return new ResponseEntity<>("Product deleted successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    public PageDTO<ProductDTO> getAllProduct(PageInput pageInput) {
        if (pageInput.getSearch().length() == 0) {
            Page<Product> page = productRepository.findAll(
                    PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())));
            return new PageDTO<>(page.getNumber(), (int) page.getTotalPages(), page.getSize(),
                    page.getContent().stream().map(ProductDTO::new).toList());
        }
        Page<Product> page = productRepository.findAllByNameContainingIgnoreCase(pageInput.getSearch(),
                PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())));
        return new PageDTO<>(page.getNumber(), (int) page.getTotalPages(), page.getSize(),
                page.getContent().stream().map(ProductDTO::new).toList());
    }
    public List<ProductDTO> getAllProduct() {
        try {
            return productRepository.findAll().stream().map(ProductDTO::new).toList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
