package com.nodeajva.ch3schedule.exception;

public class CommentNotFoundException extends RuntimeException {

	public CommentNotFoundException() {
		super("댓글을 찿을수 없습니다");
	}

}
