package com.nodeajva.ch3schedule.dto.response;

import com.nodeajva.ch3schedule.entity.User;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class SignupResponse{

    private Long id;
    private String loginId;
    private String userName;
    private String email;



    public static SignupResponse from(User user) {
        return SignupResponse.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .build();
    }
}
