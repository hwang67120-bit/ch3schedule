package com.nodeajva.ch3schedule.dto.response;

import com.nodeajva.ch3schedule.Entity.Schedule;
import com.nodeajva.ch3schedule.Entity.User;
import lombok.Builder;

import java.time.LocalDateTime;


public record ScheduleResponse(

        Long id,
        String title,
        String content,
        Long userId,
        String userName,
        LocalDateTime createdAt,
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
