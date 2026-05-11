package com.nodeajva.ch3schedule.dto.request;



import jakarta.validation.constraints.NotBlank;


public record LoginRequest(

        @NotBlank(message = "로그인 정보가 맞지 않습니다")
        String loginId,

        @NotBlank(message = "로그인 정보가 맞지 않습니다")
        String password
) {
}
