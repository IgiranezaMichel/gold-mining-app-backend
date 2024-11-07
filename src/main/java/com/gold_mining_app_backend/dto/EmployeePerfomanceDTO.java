package com.gold_mining_app_backend.dto;

import java.text.DecimalFormat;
import com.gold_mining_app_backend.modal.EmployeePerfomance;
import com.gold_mining_app_backend.util.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeePerfomanceDTO {
private String id;
private String minedGoldQty;
private String timeStamp;
private AttendanceDTO attendanceDTO;
DecimalFormat df=new DecimalFormat("#00");
public EmployeePerfomanceDTO(EmployeePerfomance ep){
this.minedGoldQty=df.format(ep.getMinedGoldQty());
this.timeStamp=LocalDateTimeConverter.convertLocalDateTime(ep.getTimeStamp(), "dd,MMMM-yyy HH;ss a");
this.attendanceDTO=new AttendanceDTO(ep.getAttendance());
}
}