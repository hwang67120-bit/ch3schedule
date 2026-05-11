package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.entity.Comment;
import com.nodeajva.ch3schedule.exception.CommentNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 일정 ID로 댓글 목록 조회
     *
     * @param scheduleId 일정 ID
     * @return 해당 일정의 댓글 목록
     */
    List<Comment> findByScheduleId(Long scheduleId);

    /**
     * 일정 ID로 댓글 삭제
     *
     * @param scheduleId 일정 ID
     */
    void deleteByScheduleId(Long scheduleId);

    /**
     * ID로 댓글 조회
     *
     * @param id 댓글 ID
     * @return 댓글 엔티티
     * @throws CommentNotFoundException 댓글을 찾을 수 없는 경우
     */
    default Comment load(Long id) {
        return findById(id).orElseThrow(CommentNotFoundException::new);
    }
}
