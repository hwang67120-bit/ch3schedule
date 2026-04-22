package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.entity.Comment;
import com.nodeajva.ch3schedule.entity.Schedule;
import com.nodeajva.ch3schedule.exception.CommentNotFoundException;
import com.nodeajva.ch3schedule.exception.ScheduleNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleId(Long scheduleId);

    void deleteByScheduleId(Long scheduleId);

    default Comment load(Long id) {
        return findById(id).orElseThrow(CommentNotFoundException::new);
    }
}
