package com.gold_mining_app_backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gold_mining_app_backend.dto.UserDTO;
import com.gold_mining_app_backend.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class AuthenticationController {
@Autowired private UserRepository userRepository;
   @RequestMapping(value="/login")
 public ResponseEntity<String> login()
 {
   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if(!auth.getName().equals("anonymousUser")&&!auth.getPrincipal().toString().equals("anonymousUser"))
    {
      return new ResponseEntity<>("login successful",HttpStatus.OK);
    }
   return new ResponseEntity<>("Wrong username or password",HttpStatus.BAD_REQUEST);
 }
 @RequestMapping("/success-login")
public UserDTO successLogin(Principal principal){
    return userRepository.findByEmail(principal.getName())
    .stream().map(usr->new UserDTO(usr)).findFirst().orElseThrow(()->new RuntimeException("User not found"));
}
@RequestMapping("/fail-login")
public ResponseEntity<String> failLogin(){
return new ResponseEntity<>("Wrong username or password",HttpStatus.BAD_REQUEST);
}
}