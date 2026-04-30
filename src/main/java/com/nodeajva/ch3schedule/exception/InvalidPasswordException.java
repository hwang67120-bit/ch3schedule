package com.nodeajva.ch3schedule.exception;

public class InvalidPasswordException extends RuntimeException {

	public InvalidPasswordException() {
		super("아이디 또는 비밀번호가 틀렸습니다");
	}
}
