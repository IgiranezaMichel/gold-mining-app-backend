package com.gold_mining_app_backend.dto;

import com.gold_mining_app_backend.modal.Incident;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncidentDTO {
    private UserDTO user;
    private String descritpion;
    private String id;

    public IncidentDTO(Incident incident) {
        this.user = new UserDTO(incident.getUserPosted());
        this.descritpion = incident.getDescription();
        this.id = incident.getId().toString();
    }
}
