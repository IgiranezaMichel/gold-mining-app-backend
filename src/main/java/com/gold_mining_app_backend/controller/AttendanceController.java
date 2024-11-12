package com.gold_mining_app_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gold_mining_app_backend.dto.AttendanceDTO;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.enums.ProductQuality;
import com.gold_mining_app_backend.input.AttendanceInput;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.services.AttendanceServices;

@RestController
@CrossOrigin
@RequestMapping("/api/attendance")
public class AttendanceController {
  @Autowired
    private AttendanceServices attendanceServices;

    @PostMapping("add-attendance")
    public ResponseEntity<String> createAttendance(@RequestBody AttendanceInput AttendanceInput) {
        return attendanceServices.createAttendance(AttendanceInput);
    }
    @PostMapping("delete-Attendance/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable String  id) {
        return attendanceServices.deleteAttendance(id);
    }
    @GetMapping("get/detail/{AttendanceId}")
    public AttendanceDTO getAttendanceDetail(@PathVariable String AttendanceId) {
        return attendanceServices.getAttendanceDetailPrinciple(AttendanceId);
    }

    @GetMapping("find-all/Attendance/{productId}")
    public PageDTO<AttendanceDTO> getAllProductAttendancePageList(@RequestBody PageInput pageInput, @RequestParam ProductQuality productQuality) {
        return attendanceServices.getAllAttendancePageList(pageInput, productQuality);
    }
}
