package com.gold_mining_app_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import com.gold_mining_app_backend.input.IncidentInput;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Incident {
@UuidGenerator(style = Style.AUTO)
@Id
private UUID id;
@Column(columnDefinition = "text")
private String description;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = User.class)
private User userPosted;
private LocalDateTime timeStamp;
public Incident(IncidentInput in,User user){
    if(in.getId()!=null&&!in.getId().equals(""))this.id=UUID.fromString(in.getId());
    if(user==null)throw new RuntimeException("User is required");
    this.userPosted=user;
    if(in.getDescription()!=null&&in.getDescription().equals(""))throw new RuntimeException("Description is required");
    this.description=in.getDescription();
    this.timeStamp=LocalDateTime.now();
}

}
