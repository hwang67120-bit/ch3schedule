package com.nodeajva.ch3schedule.dto.response;

import com.nodeajva.ch3schedule.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponse(

        Long id,
        String content,
        Long scheduleId,
        String scheduleTitle,
        Long userId,
        String userName,
        LocalDateTime craeatedAt,
        LocalDateTime updatedAt

) {


    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getSchedule().getId(),
                comment.getSchedule().getTitle(),
                comment.getUser().getId(),
                comment.getUser().getUserName(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()

        );
    }
}
