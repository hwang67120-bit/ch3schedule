package com.nodeajva.ch3schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nodeajva.ch3schedule.dto.request.CommentRequest;
import com.nodeajva.ch3schedule.dto.response.CommentResponse;
import com.nodeajva.ch3schedule.entity.Comment;
import com.nodeajva.ch3schedule.entity.Schedule;
import com.nodeajva.ch3schedule.entity.User;
import com.nodeajva.ch3schedule.exception.CommentNotFoundException;
import com.nodeajva.ch3schedule.exception.ScheduleNotFoundException;
import com.nodeajva.ch3schedule.exception.UserNotFoundException;
import com.nodeajva.ch3schedule.repository.CommentRepository;
import com.nodeajva.ch3schedule.repository.ScheduleRepository;
import com.nodeajva.ch3schedule.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

	private final UserRepository userRepository;
	private final ScheduleRepository scheduleRepository;
	private final CommentRepository commentRepository;

	/**
	 * 댓글 등록
	 * 특정 일정에 새로운 댓글을 작성합니다.
	 *
	 * @param request 댓글 등록 요청 (scheduleId, userId, content)
	 * @return 생성된 댓글 정보 (id, content, scheduleId, userId, userName, createdAt, updatedAt)
	 * @throws ScheduleNotFoundException 존재하지 않는 일정인 경우
	 * @throws UserNotFoundException     존재하지 않는 사용자인 경우
	 */
	@Transactional
	public CommentResponse save(CommentRequest request) {

		Schedule schedule = scheduleRepository.load(request.scheduleId());

		User author = userRepository.load(request.userId());

		Comment newcomment = new Comment(
			request.content(),
			schedule,
			author
		);

		return CommentResponse.from(commentRepository.save(newcomment));
	}

	/**
	 * 일정의 댓글 조회
	 * 특정 일정에 작성된 모든 댓글을 조회합니다.
	 *
	 * @param scheduleId 일정 ID
	 * @return 댓글 목록 (id, content, scheduleId, userId, userName, createdAt, updatedAt)
	 */
	public List<CommentResponse> findByScheduleId(Long scheduleId) {

		List<Comment> comments = commentRepository.findByScheduleId(scheduleId);

		List<CommentResponse> responses = new ArrayList<>();
		for (Comment comment : comments) {
			responses.add(CommentResponse.from(comment));
		}

		return responses;

	}

	/**
	 * 댓글 수정
	 * 기존 댓글의 내용을 수정합니다.
	 *
	 * @param id      댓글 ID
	 * @param request 댓글 수정 요청 (scheduleId, userId, content)
	 * @return 수정된 댓글 정보 (id, content, scheduleId, userId, userName, createdAt, updatedAt)
	 * @throws CommentNotFoundException 존재하지 않는 댓글인 경우
	 */
	@Transactional
	public CommentResponse update(Long id, CommentRequest request) {
		Comment comment = commentRepository.load(id);

		comment.update(request.content());

		return CommentResponse.from(comment);

	}

	/**
	 * 댓글 삭제
	 * 특정 댓글을 삭제합니다.
	 *
	 * @param id 댓글 ID
	 * @throws CommentNotFoundException 존재하지 않는 댓글인 경우
	 */
	@Transactional
	public void delete(Long id) {
		Comment comment = commentRepository.load(id);

		commentRepository.delete(comment);
	}
}
