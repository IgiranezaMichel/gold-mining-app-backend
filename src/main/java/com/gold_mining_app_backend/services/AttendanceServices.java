package com.gold_mining_app_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gold_mining_app_backend.dto.AttendanceDTO;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.enums.AttendanceStatus;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.input.AttendanceInput;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.repository.AttendanceRepository;

@Service
public class AttendanceServices {
@Autowired private AttendanceRepository attendanceRepository;

public ResponseEntity<String> createAttendance(AttendanceInput attendanceInput,AttendanceStatus status) {
    try {
        if(status==AttendanceStatus.PRESENT){

        }
        else if(status==AttendanceStatus.LEAVE){
            
        }
        else if(status==AttendanceStatus.HOLIDAY){

        }else{
            
        }
        return ResponseEntity.ok("Attendance Added Successfully");
    } catch (Exception e) {
       return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

public ResponseEntity<String> deleteAttendance(String id) {
    // p0-TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteAttendance'");
}

public AttendanceDTO getAttendanceDetailPrinciple(String attendanceId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAttendanceDetailPrinciple'");
}

public PageDTO<AttendanceDTO> getAllAttendancePageList(PageInput pageInput, ProductQuality productQuality) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllAttendancePageList'");
}
}
