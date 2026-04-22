package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.entity.User;
import com.nodeajva.ch3schedule.exception.InvalidPasswordException;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByLoginId(String loginId);

    default User getByLoginId(String loginId) {
        return findByLoginId(loginId).orElseThrow(InvalidPasswordException::new);
    }


}
