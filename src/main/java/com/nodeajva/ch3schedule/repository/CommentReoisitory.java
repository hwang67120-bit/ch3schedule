package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReoisitory extends JpaRepository<Comment, Long> {
}
