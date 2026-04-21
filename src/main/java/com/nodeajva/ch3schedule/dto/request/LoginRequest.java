package com.nodeajva.ch3schedule.dto.request;



import jakarta.validation.constraints.NotNull;

public record LoginRequest(

        @NotNull(message = "로그인 정보가 맞지 않습니다")
        String loginId,

        @NotNull(message = "로그인 정보가 맞지 않습니다")
        String password
) {
}
