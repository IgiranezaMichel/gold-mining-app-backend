package com.gold_mining_app_backend.input;
import java.time.LocalDateTime;

import com.gold_mining_app_backend.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AttendanceInput {
private String id;
private LocalDateTime timeStamp;
private LocalDateTime checkInTime;
private LocalDateTime checkOutTime;
private AttendanceStatus status;
private String userId;
}
