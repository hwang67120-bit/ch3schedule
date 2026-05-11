package com.nodeajva.ch3schedule.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ScheduleUpdateRequest(

        @NotBlank
        @Size(max = 10)
        String title,

        @Size(max = 200)
        String content

        // userId 없음! ← 수정 시 불필요!
) {
}