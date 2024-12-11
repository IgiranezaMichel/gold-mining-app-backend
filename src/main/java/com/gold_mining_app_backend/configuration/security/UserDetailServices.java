package com.gold_mining_app_backend.configuration.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gold_mining_app_backend.modal.User;
@Service
public class UserDetailServices implements UserDetailsService {
    @Autowired private com.gold_mining_app_backend.repository.UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User accountHolder = userRepository.findByEmail(username).stream().findFirst().orElseThrow(()->new UsernameNotFoundException("Unimplemented method  loadUserByUsername"));
        return new UserDetailPrinciple(accountHolder);
    }
}