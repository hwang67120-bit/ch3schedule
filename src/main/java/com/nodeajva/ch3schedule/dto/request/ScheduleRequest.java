package com.nodeajva.ch3schedule.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ScheduleRequest(

        @NotBlank(message = "제목은 필수입니다")
        @Size(max = 10, message = "제목은 10글자 이내여야합니다")
        String title,

        @Size(max = 200, message = "내용은 200글자 이내여야 합니다.")
        String content,

        @NotNull(message = "작성자 아이디는 필수 입니다")
        Long userId
) {



}
