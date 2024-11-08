package com.gold_mining_app_backend.input;

import java.time.LocalDateTime;
import java.util.UUID;

import com.gold_mining_app_backend.enums.Role;
import com.gold_mining_app_backend.enums.USER_STATUS;
import com.gold_mining_app_backend.modal.User;
import lombok.Getter;
import lombok.Setter;

public class UserInput extends User {
    @Getter
    @Setter
    private String base64Image;
    public UserInput(UUID id, byte[] picture, String name, String email, String phoneNumber, Role role,
            USER_STATUS status, String password, String base64Image) {
        super(id, picture, name, base64Image, email, phoneNumber, role, status, password, LocalDateTime.now());
        this.base64Image = base64Image;
    }
}
