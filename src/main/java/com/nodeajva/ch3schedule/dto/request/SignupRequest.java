package com.nodeajva.ch3schedule.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignupRequest(

        @NotBlank(message = "로그인 아이디는 필수입니다.")
        @Size(min = 4, max = 10, message = "로그인 아이디는 4~10글자여야 합니다")
        String loginId,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min =8 , message = "비밀번호는 최소 8글자 이상이여야 합니다")
        String password,

        @NotBlank(message = "이름은 필수입니다.")
        @Size(min = 4, message = "이름은 4글자 이내여야 합니다")
        String userName,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        String email
) {
}
