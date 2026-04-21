package com.nodeajva.ch3schedule.service;

import com.nodeajva.ch3schedule.Entity.User;
import com.nodeajva.ch3schedule.config.PasswoedEncoder;
import com.nodeajva.ch3schedule.dto.request.LoginRequest;
import com.nodeajva.ch3schedule.dto.request.SignupRequest;
import com.nodeajva.ch3schedule.dto.response.LoginResponse;
import com.nodeajva.ch3schedule.dto.response.SignupResponse;
import com.nodeajva.ch3schedule.exception.DuplicateLoginIdException;
import com.nodeajva.ch3schedule.exception.InvalidPasswordException;
import com.nodeajva.ch3schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswoedEncoder passwoedEncoder;

    //회원가입
    public SignupResponse signup(SignupRequest request){



        if (userRepository.findByLoginId(request.loginId()).isPresent()) {
            throw new DuplicateLoginIdException();
        }

        String encodedPassword = passwoedEncoder.encode(request.password());

        User user = new User(
                request.loginId(),
                encodedPassword,
                request.email(),
                request.userName()
        );

        User saved = userRepository.save(user);

        return SignupResponse.from(saved);
    }

    //로그인
    public LoginResponse login(LoginRequest login ){

        User user = userRepository.findByLoginId(login.loginId())
                .orElseThrow(() -> new InvalidPasswordException());

        if (!user.getPassword().equals(login.password())) {
            throw new InvalidPasswordException();
        }
        return LoginResponse.from(user);

    }
}
