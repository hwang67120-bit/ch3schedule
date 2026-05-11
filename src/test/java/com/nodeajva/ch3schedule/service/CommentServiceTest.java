package com.nodeajva.ch3schedule.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nodeajva.ch3schedule.dto.request.CommentRequest;
import com.nodeajva.ch3schedule.dto.response.CommentResponse;
import com.nodeajva.ch3schedule.entity.Comment;
import com.nodeajva.ch3schedule.entity.Schedule;
import com.nodeajva.ch3schedule.entity.User;
import com.nodeajva.ch3schedule.repository.CommentRepository;
import com.nodeajva.ch3schedule.repository.ScheduleRepository;
import com.nodeajva.ch3schedule.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private ScheduleRepository scheduleRepository;

	@Mock
	private CommentRepository commentRepository;

	@InjectMocks
	private CommentService commentService;

	@Test
	@DisplayName("댓글 생성 성공")
	void save_댓글_생성_성공() {
		// given
		Long scheduleId = 1L;
		Long userId = 1L;
		String content = "테스트 댓글";

		CommentRequest request = new CommentRequest(content, userId, scheduleId);

		User user = new User("testId", "password123", "테스터", "test@test.com");
		Schedule schedule = new Schedule("테스트 일정", "일정 내용", user);
		Comment comment = new Comment(content, schedule, user);

		given(scheduleRepository.load(scheduleId)).willReturn(schedule);
		given(userRepository.load(userId)).willReturn(user);
		given(commentRepository.save(any(Comment.class))).willReturn(comment);

		// when
		CommentResponse response = commentService.save(request);

		// then
		assertNotNull(response);
		verify(scheduleRepository).load(scheduleId);
		verify(userRepository).load(userId);
		verify(commentRepository).save(any(Comment.class));
	}

	@Test
	@DisplayName("일정별 댓글 조회 성공")
	void findByScheduleId_조회_성공() {
		// given
		Long scheduleId = 1L;

		User user = new User("testId", "password123", "테스터", "test@test.com");
		Schedule schedule = new Schedule("테스트 일정", "일정 내용", user);

		Comment comment1 = new Comment("댓글1", schedule, user);
		Comment comment2 = new Comment("댓글2", schedule, user);

		given(commentRepository.findByScheduleId(scheduleId))
			.willReturn(List.of(comment1, comment2));

		// when
		List<CommentResponse> responses = commentService.findByScheduleId(scheduleId);

		// then
		assertEquals(2, responses.size());
		verify(commentRepository).findByScheduleId(scheduleId);
	}

	@Test
	@DisplayName("댓글 수정 성공")
	void update_댓글_수정_성공() {
		// given
		Long commentId = 1L;
		Long scheduleId = 1L;
		Long userId = 1L;
		String newContent = "수정된 댓글";

		CommentRequest request = new CommentRequest(newContent, userId, scheduleId);

		User user = new User("testId", "password123", "테스터", "test@test.com");
		Schedule schedule = new Schedule("테스트 일정", "일정 내용", user);
		Comment comment = new Comment("원래 댓글", schedule, user);

		given(commentRepository.load(commentId)).willReturn(comment);

		// when
		CommentResponse response = commentService.update(commentId, request);

		// then
		assertNotNull(response);
		verify(commentRepository).load(commentId);
	}

	@Test
	@DisplayName("댓글 삭제 성공")
	void delete_댓글_삭제_성공() {
		// given
		Long commentId = 1L;

		User user = new User("testId", "password123", "테스터", "test@test.com");
		Schedule schedule = new Schedule("테스트 일정", "일정 내용", user);
		Comment comment = new Comment("삭제할 댓글", schedule, user);

		given(commentRepository.load(commentId)).willReturn(comment);

		// when
		commentService.delete(commentId);

		// then
		verify(commentRepository).load(commentId);
		verify(commentRepository).delete(comment);
	}
}