package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.Entity.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {





    List<Schedule> findAllByOrderByCreatedAtDesc();

    List<Schedule> findByUserId(Long userid);
}
