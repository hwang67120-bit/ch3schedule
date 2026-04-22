package com.nodeajva.ch3schedule.dto.response;

import com.nodeajva.ch3schedule.entity.Schedule;

import java.time.LocalDateTime;


public record ScheduleResponse(

        Long id,
        String title,
        String content,
        Long userId,
        String userName,
        LocalDateTime craeatedAt,
        LocalDateTime updatedAt


) {

    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getUser().getUserName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );

    }
}
