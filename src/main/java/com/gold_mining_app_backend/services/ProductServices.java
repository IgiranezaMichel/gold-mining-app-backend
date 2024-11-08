package com.gold_mining_app_backend.services;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gold_mining_app_backend.dto.ProductDTO;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.modal.Product;
import com.gold_mining_app_backend.repository.ProductRepository;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<String> createPrduct(Product product) {
        try {
            Product dbProduct=productRepository.findByQualityAndCategory(product.getQuality(),product.getCategory()).orElse(null);
            if(dbProduct!=null){
                product.setId(dbProduct.getId());
            }
            productRepository.save(product);
            if(dbProduct==null)
            return new ResponseEntity<>("Product saved successful", HttpStatus.CREATED);
            return new ResponseEntity<>("Product updated successful", HttpStatus.CREATED);

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

    public List<ProductDTO> getAllProduct(PageInput pageInput) {
        return productRepository.findAll(
                PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())))
                .getContent().stream().map(ProductDTO::new).toList();
    }
}
