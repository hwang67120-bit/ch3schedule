package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.entity.Schedule;
import com.nodeajva.ch3schedule.exception.ScheduleNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {





    List<Schedule> findAllByOrderByCreatedAtDesc();

    List<Schedule> findByUserId(Long userid);

    default Schedule getById(Long id) {
        return findById(id).orElseThrow(ScheduleNotFoundException::new);
    }
}
