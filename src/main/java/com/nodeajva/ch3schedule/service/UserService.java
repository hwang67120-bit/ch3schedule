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
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * 새로운 사용자를 등록합니다.
     *
     * @param request 회원가입 요청 (loginId, password, userName, email)
     * @return 생성된 사용자 정보 (userId, userName, email, createdAt, updatedAt)
     * @throws DuplicateLoginIdException 이미 존재하는 로그인 ID인 경우
     */
    @Transactional
    public SignupResponse signup(SignupRequest request){

        if (userRepository.findByLoginId(request.loginId()).isPresent()) {
            throw new DuplicateLoginIdException();
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        User newuser = new User(
                request.loginId(),
                encodedPassword,
                request.userName(),
                request.email()
        );

        return SignupResponse.from(userRepository.save(newuser));
    }

    /**
     * 로그인
     * 사용자 인증을 수행합니다.
     * 보안을 위해 사용자 존재 여부와 비밀번호 오류를 구분하지 않습니다.
     *
     * @param request 로그인 요청 (loginId, password)
     * @return 로그인 응답 (userId, loginId, userName)
     * @throws InvalidPasswordException 로그인 실패 시 (사용자 없음 또는 비밀번호 불일치)
     */
    @Transactional
    public LoginResponse login(LoginRequest request ){

        User loginUser = userRepository.loadByLoginId(request.loginId());

        if (!passwordEncoder.matches(request.password(), loginUser.getPassword())) {
            throw new InvalidPasswordException();
        }
        return LoginResponse.from(loginUser);

    }

}
