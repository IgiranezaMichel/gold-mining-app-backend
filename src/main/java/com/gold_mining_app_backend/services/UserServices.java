package com.gold_mining_app_backend.services;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.dto.UserDTO;
import com.gold_mining_app_backend.enums.Role;
import com.gold_mining_app_backend.enums.USER_STATUS;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.input.UserInput;
import com.gold_mining_app_backend.modal.User;
import com.gold_mining_app_backend.repository.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> createUserAccount(UserInput userInput) {
        try {
            userRepository.save(new User(userInput));
            return new ResponseEntity<>("Account is created successful", HttpStatus.CREATED);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                return new ResponseEntity<>("User is already exist", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public UserDTO getUserDetailPrinciple(Principal principal) {
        return userRepository.findByEmail(principal.getName()).stream().map(UserDTO::new).findFirst().orElse(null);
    }

    public PageDTO<UserDTO> getAllUserPageList(PageInput pageInput, Role role, USER_STATUS status) {
        Page<User> page = userRepository.findAllByRoleAndStatus(
                PageRequest.of(pageInput.getPageNumber(), pageInput.getPageSize(), Sort.by(pageInput.getSortBy())),
                role, status);
        return new PageDTO<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                page.getContent().stream().map(UserDTO::new).toList());

    }

    public ResponseEntity<String> deleteUser(String id) {
        try {
            User user = userRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new RuntimeException("User not found"));
            userRepository.delete(user);
            return new ResponseEntity<>("User deleted sucessful", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
