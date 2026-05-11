package com.nodeajva.ch3schedule.dto.response;


import com.nodeajva.ch3schedule.entity.User;


public record LoginResponse(

        Long userId,
        String loginId,
        String userName
) {


    public static LoginResponse from(User user) {
        return new LoginResponse(
                user.getId(),
                user.getLoginId(),
                user.getUserName()
        );
    }
}
