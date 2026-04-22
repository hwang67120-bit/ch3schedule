package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleId(Long scheduleId);

    void deleteByScheduleId(Long scheduleId);
}
