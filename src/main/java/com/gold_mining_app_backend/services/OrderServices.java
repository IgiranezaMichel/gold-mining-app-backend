package com.gold_mining_app_backend.services;

import java.security.Principal;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gold_mining_app_backend.dto.OrderDTO;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.enums.OrderStatus;
import com.gold_mining_app_backend.input.OrderInput;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.modal.Orders;
import com.gold_mining_app_backend.modal.Product;
import com.gold_mining_app_backend.modal.User;
import com.gold_mining_app_backend.repository.OrderRepository;
import com.gold_mining_app_backend.repository.ProductRepository;
import com.gold_mining_app_backend.repository.UserRepository;

@Service
public class OrderServices {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<String> createOrder(OrderInput order, Principal principal) {
        try {
            User usr = userRepository.findByEmail(principal.getName())
                    .orElseThrow(() -> new RuntimeException("Cant process your order since user can't be found"));
            Product product = productRepository.findById(UUID.fromString(order.getProductId()))
                    .orElseThrow(() -> new RuntimeException("Cant process your order since product can't be found"));
            orderRepository.save(new Orders(order, usr, product));
            return new ResponseEntity<>("Order Placed Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);

        }
    }

    public ResponseEntity<String> cancelOrder(String id, OrderStatus status) {
        try {
            Orders order = orderRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new RuntimeException("Cant process your order since order can't be found"));
            order.setStatus(status);
            orderRepository.save(order);
            return new ResponseEntity<>("Order Cancelled Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    public PageDTO<OrderDTO> getAllOrderHavingSameStatus(PageInput pageInput, OrderStatus orderStatus) {
        Page<Orders> page = orderRepository.findAllByStatus(
                PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())),
                orderStatus);
        return new PageDTO<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                page.getContent().stream().map(OrderDTO::new).toList());

    }

    public PageDTO<OrderDTO> getAllPrincipleUserOrderPageHavingSameStatus(Principal principal, PageInput pageInput,
            OrderStatus orderStatus) {
        Page<Orders> page = orderRepository.findAllByUserEmailAndStatus(
                PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())),
                principal.getName(), orderStatus);
        return new PageDTO<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                page.getContent().stream().map(OrderDTO::new).toList());
    }
}
