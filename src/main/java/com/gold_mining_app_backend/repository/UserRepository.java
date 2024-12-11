package com.gold_mining_app_backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gold_mining_app_backend.enums.Role;
import com.gold_mining_app_backend.enums.USER_STATUS;
import com.gold_mining_app_backend.modal.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String name);

    Page<User> findAllByRoleAndStatus(PageRequest of, Role role, USER_STATUS status);

    @Query("SELECT COUNT(*) FROM User WHERE role = :role")
    long countByRole(Role role);

    @Query("SELECT u FROM User u WHERE u.status = :status")
    Page<User> findAllByRole(PageRequest of, USER_STATUS status);

    Page<User> findAllByRoleAndStatusAndNameContainingIgnoreCase(PageRequest of, Role role, USER_STATUS status,
            String search);

    Page<User> findAllByStatus(PageRequest of, USER_STATUS status);

    Page<User> findAllByStatusAndNameContainingIgnoreCase(PageRequest of, USER_STATUS status, String search);
}
