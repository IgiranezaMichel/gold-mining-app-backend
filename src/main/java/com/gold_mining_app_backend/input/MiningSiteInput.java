package com.gold_mining_app_backend.input;
import lombok.*;
import com.gold_mining_app_backend.enums.MiningSiteStatus;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class MiningSiteInput {
    private String id;
    private String name;
    private String address;
    private MiningSiteStatus status;
   
}
