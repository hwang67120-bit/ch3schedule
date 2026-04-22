package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.entity.User;
import com.nodeajva.ch3schedule.exception.InvalidPasswordException;
import com.nodeajva.ch3schedule.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 로그인 ID로 사용자 조회
     *
     * @param loginId 로그인 ID
     * @return Optional로 감싼 사용자 엔티티
     */
    Optional<User> findByLoginId(String loginId);

    /**
     * 로그인 ID로 사용자 조회 (예외 발생)
     *
     * @param loginId 로그인 ID
     * @return 사용자 엔티티
     * @throws InvalidPasswordException 사용자를 찾을 수 없는 경우
     */
    default User loadByLoginId(String loginId) {
        return findByLoginId(loginId).orElseThrow(InvalidPasswordException::new);
    }

    /**
     * ID로 사용자 조회
     *
     * @param id 사용자 ID
     * @return 사용자 엔티티
     * @throws UserNotFoundException 사용자를 찾을 수 없는 경우
     */
    default User load(Long id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }

}
