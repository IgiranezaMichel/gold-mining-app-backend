package com.gold_mining_app_backend.modal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.gold_mining_app_backend.enums.Role;
import com.gold_mining_app_backend.enums.USER_STATUS;
import com.gold_mining_app_backend.input.UserInput;
import com.gold_mining_app_backend.util.ImageConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
@UuidGenerator(style = Style.AUTO)
@Id
private UUID id;
private byte [] picture;
private String name;
private String gender;
@Column(unique = true)
private String email;
private String phoneNumber;
@Enumerated(EnumType.STRING)
private Role role;
@Enumerated(EnumType.STRING)
private USER_STATUS status;
private String password;
private LocalDateTime timeStamp;
public User(UUID id, byte[] picture, String name, String gender, String email, String phoneNumber, Role role,
        USER_STATUS status, String password, LocalDateTime timeStamp) {
    this.id = id;
    this.picture = picture;
    this.name = name;
    this.gender = gender;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
    this.status = status;
    this.password = password;
    this.timeStamp = timeStamp;
}
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "user",targetEntity = Attendance.class)
public List<Attendance>attendanceList;
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "user",targetEntity = Orders.class)
public List<Orders>orderList;
public User(UserInput in){
if(!in.getId().equals("")&&in.getId()!=null)this.id=UUID.fromString(in.getId());
this.picture=ImageConverter.convertToBase64(in.getBase64Image());
this.timeStamp=LocalDateTime.now();
if(in.getName().equals(""))throw new RuntimeException("Name is required");
this.name=in.getName();
if(in.getGender().equals(""))throw new RuntimeException("Gender is required");
this.gender=in.getGender();
if(in.getEmail().equals(""))throw new RuntimeException("Email is required");
this.email=in.getEmail();
if(in.getPhoneNumber().equals(""))throw new RuntimeException("Phone number is required");
this.phoneNumber=in.getPhoneNumber();
if(in.getPassword().equals(""))throw new RuntimeException("Password is required");
this.password=BCrypt.hashpw(in.getPassword(), BCrypt.gensalt());
this.status=USER_STATUS.ACTIVE;
if(in.getRole().name().equals(""))throw new RuntimeException("Role is required");
this.role=in.getRole();
}
}
