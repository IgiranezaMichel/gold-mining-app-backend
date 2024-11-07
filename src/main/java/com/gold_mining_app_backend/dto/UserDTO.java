package com.gold_mining_app_backend.dto;

import com.gold_mining_app_backend.enums.Role;
import com.gold_mining_app_backend.enums.USER_STATUS;
import com.gold_mining_app_backend.modal.User;
import com.gold_mining_app_backend.util.ImageConverter;
import com.gold_mining_app_backend.util.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
private String id;
private String picture;
private String name;
private String gender;
private String email;
private String phoneNumber;
private Role role;
private USER_STATUS status;
private String timeStamp;
public UserDTO(User u){
    this.id=u.getId().toString();
    if(u.getPicture()!=null)
    this.picture=ImageConverter.convertToString(u.getPicture());
    this.name=u.getName();
    this.gender=u.getGender();
    this.email=u.getEmail();
    this.phoneNumber=u.getPhoneNumber();
    this.role=u.getRole();
    this.status=u.getStatus();
    this.timeStamp=LocalDateTimeConverter.convertLocalDateTime(u.getTimeStamp(), "dd,MMMM-yyyy");
}
}