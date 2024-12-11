package com.gold_mining_app_backend.input;
import com.gold_mining_app_backend.enums.Role;
import com.gold_mining_app_backend.enums.USER_STATUS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInput {
    private String id;
    private String name;
    private String gender;
    private String email;
    private String phoneNumber;
    private Role role;
    private USER_STATUS status;
    private String password;
    private String base64Image;
}
