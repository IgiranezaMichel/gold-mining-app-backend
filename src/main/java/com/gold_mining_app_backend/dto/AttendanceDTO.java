package com.gold_mining_app_backend.dto;
import com.gold_mining_app_backend.enums.AttendanceStatus;
import com.gold_mining_app_backend.modal.Attendance;
import com.gold_mining_app_backend.util.LocalDateTimeConverter;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceDTO {
private String id;
private String timeStamp;
private String checkInTime;
private String checkOutTime;
@Enumerated(EnumType.STRING)
private AttendanceStatus status;
private UserDTO user;
public AttendanceDTO(Attendance at){
    this.id=at.getId().toString();
    this.timeStamp=LocalDateTimeConverter.convertLocalDateTime(at.getTimeStamp(), "dd,MMMM-yyy HH:mm a");
    this.checkInTime=LocalDateTimeConverter.convertLocalDateTime(at.getCheckInTime(), "dd,MMMM-yyy HH:mm a");
    this.checkOutTime=LocalDateTimeConverter.convertLocalDateTime(at.getCheckOutTime(), "dd,MMMM-yyy HH:mm a");
    this.status=at.getStatus();
    this.user=new UserDTO(at.getUser());
}
}
