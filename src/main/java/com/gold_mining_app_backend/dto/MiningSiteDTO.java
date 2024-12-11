package com.gold_mining_app_backend.dto;

import java.time.LocalDateTime;
import com.gold_mining_app_backend.modal.MiningSite;
import com.gold_mining_app_backend.enums.MiningSiteStatus;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MiningSiteDTO {
    private String id;
    private String name;
    private String address;
    private MiningSiteStatus status;
    private LocalDateTime timeStamp;

    public MiningSiteDTO(MiningSite miningSiteInput) {
        this.id = miningSiteInput.getId().toString();
        this.name = miningSiteInput.getName();
        this.address = miningSiteInput.getAddress();
        this.status = miningSiteInput.getStatus();
        this.timeStamp = LocalDateTime.now();
    }
}
