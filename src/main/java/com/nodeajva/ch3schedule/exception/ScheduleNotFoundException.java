package com.nodeajva.ch3schedule.exception;

public class ScheduleNotFoundException extends RuntimeException {

	public ScheduleNotFoundException() {
		super("일정을 찾을 수 없습니다.");
	}

}
