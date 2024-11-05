package com.gold_mining_app_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gold_mining_app_backend.modal.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance,UUID> {

}
