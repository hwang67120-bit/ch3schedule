package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.entity.Schedule;
import com.nodeajva.ch3schedule.exception.ScheduleNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {





    List<Schedule> findAllByOrderByCreatedAtDesc();

    List<Schedule> findByUserId(Long userid);

    default Schedule load(Long id) {
        return findById(id).orElseThrow(ScheduleNotFoundException::new);
    }

    @Query("SELECT s FROM Schedule s ORDER BY s.createdAt  DESC")
    List<Schedule> findRecent();
}
