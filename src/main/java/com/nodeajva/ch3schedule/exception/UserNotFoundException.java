package com.nodeajva.ch3schedule.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("사용자를 찿을수 없습니다");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
