package com.nodeajva.ch3schedule.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentRequest(

        @NotBlank(message = "내용를 입력해주세요")
        @Size(max = 100, message = "내용은 100글자 이내여야 합니다")
        String content,

        @NotNull(message = "일정 아이디는 필수 입니다")
        Long scheduleId,

        @NotNull(message = "작성자 아이디는 필수입니다")
        Long userId
) {
}
