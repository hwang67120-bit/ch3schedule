package com.nodeajva.ch3schedule.dto.response;

import java.time.LocalDateTime;

public record CommentResponse(

        Long id,
        String content,
        Long scheduleId,
        String scheduleTitle,
        Long userId,
        String userName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
