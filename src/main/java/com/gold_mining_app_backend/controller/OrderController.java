package com.gold_mining_app_backend.controller;

import java.security.Principal;
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
import com.gold_mining_app_backend.dto.OrderDTO;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.enums.OrderStatus;
import com.gold_mining_app_backend.input.OrderInput;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.services.OrderServices;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderServices orderServices;

    @PostMapping("add-or-update-order")
    public ResponseEntity<String> createOrder(@RequestBody OrderInput order,Principal principal) {
        return orderServices.createOrder(order,principal);
    }
    @PostMapping("cancel-order/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable String  id,@RequestParam OrderStatus status) {
        return orderServices.cancelOrder(id,status);
    }
        // admin principal
    @GetMapping("find-all/{orderStatus}")
    public PageDTO<OrderDTO> getAllOrderPageHavingSameStatus(@RequestBody PageInput pageInput,@PathVariable OrderStatus orderStatus) {
        return orderServices.getAllOrderHavingSameStatus(pageInput,orderStatus);
    }
    // client principal
    @GetMapping("find-all-user-order/{orderStatus}")
    public PageDTO<OrderDTO> getAllPrincipleUserOrderPageHavingSameStatus(Principal principal,@RequestBody PageInput pageInput,@PathVariable OrderStatus orderStatus) {
        return orderServices.getAllPrincipleUserOrderPageHavingSameStatus(principal,pageInput,orderStatus);
    }
}
