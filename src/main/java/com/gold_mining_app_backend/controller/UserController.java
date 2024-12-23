package com.gold_mining_app_backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.dto.UserDTO;
import com.gold_mining_app_backend.enums.Role;
import com.gold_mining_app_backend.enums.USER_STATUS;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.input.UserInput;
import com.gold_mining_app_backend.services.UserServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("create-account")
    public ResponseEntity<String> createUserAccount(@RequestBody UserInput userInput) {
        return userServices.createUserAccount(userInput);
    }
    @DeleteMapping("delete-account/{id}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable String  id) {
        return userServices.deleteUser(id);
    }
    @GetMapping("get/principle-detail")
    public UserDTO getUserDetail(Principal principal) {
        return userServices.getUserDetailPrinciple(principal);
    }

    @PostMapping("/find-all/user/role")
    public PageDTO<UserDTO> getAllUserPageList(@RequestBody PageInput pageInput, @RequestParam Role role,
            @RequestParam USER_STATUS status) {
        return userServices.getAllUserPageList(pageInput, role, status);
    }
    @PostMapping("/find-all/user")
    public PageDTO<UserDTO> getAllUserList(@RequestBody PageInput pageInput,
            @RequestParam USER_STATUS status) {
        return userServices.getAllUserList(pageInput, status);
    }
    @GetMapping("count/by/{role}")
    public long countUserByRole(@PathVariable Role role) {
        return userServices.countByRole(role);
    }
    @GetMapping("count/size")
    public long countUsers() {
        return userServices.countUsers();
    }
    @GetMapping("reset-password/{userId}")
    public ResponseEntity<String> resetUserPassword(@PathVariable String userId) {
        return userServices.resetUserPassword(userId);
    }
}
