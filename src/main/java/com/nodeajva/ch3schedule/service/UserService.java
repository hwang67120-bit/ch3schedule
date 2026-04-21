package com.nodeajva.ch3schedule.service;

import com.nodeajva.ch3schedule.entity.User;
import com.nodeajva.ch3schedule.config.PasswordEncoder;
import com.nodeajva.ch3schedule.dto.request.LoginRequest;
import com.nodeajva.ch3schedule.dto.request.SignupRequest;
import com.nodeajva.ch3schedule.dto.response.LoginResponse;
import com.nodeajva.ch3schedule.dto.response.SignupResponse;
import com.nodeajva.ch3schedule.exception.DuplicateLoginIdException;
import com.nodeajva.ch3schedule.exception.InvalidPasswordException;
import com.nodeajva.ch3schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public SignupResponse signup(SignupRequest request) {


        if (!passwordEncoder.matches(login.password(),())) {
            throw new DuplicateLoginIdException();
        }




        String encodedPassword = passwordEncoder.encode(request.password());

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
