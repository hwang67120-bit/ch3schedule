package com.nodeajva.ch3schedule.dto.response;

import com.nodeajva.ch3schedule.entity.User;


import lombok.Builder;


@Builder
public record SignupResponse(

        Long id,
        String loginId,
        String userName,
        String email) {

    public static SignupResponse from(User user) {
        return SignupResponse.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .build();
    }
}
