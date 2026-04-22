package com.nodeajva.ch3schedule.controller;

import com.nodeajva.ch3schedule.dto.request.LoginRequest;
import com.nodeajva.ch3schedule.dto.request.SignupRequest;
import com.nodeajva.ch3schedule.dto.response.LoginResponse;
import com.nodeajva.ch3schedule.dto.response.SignupResponse;
import com.nodeajva.ch3schedule.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 API
     *
     * @param request 회원가입 요청 (loginId, password, userName, email)
     * @return 생성된 사용자 정보
     */    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public SignupResponse signup(@Valid @RequestBody SignupRequest request){

        return userService.signup(request);
    }

    /**
     * 로그인 API
     *
     * @param request 로그인 요청 (loginId, password)
     * @return 로그인 응답 (userId, loginId, userName)
     */
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request){

        return userService.login(request);
    }
}
