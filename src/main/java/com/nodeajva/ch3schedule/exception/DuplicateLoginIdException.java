package com.nodeajva.ch3schedule.exception;

public class DuplicateLoginIdException extends RuntimeException {

    public DuplicateLoginIdException(){super("이미 존재하는 아이디 입니다");}
    public DuplicateLoginIdException(String message) {
        super(message);
    }
}
