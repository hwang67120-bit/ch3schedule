package com.nodeajva.ch3schedule.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nodeajva.ch3schedule.dto.request.CommentRequest;
import com.nodeajva.ch3schedule.service.CommentService;

@WebMvcTest(
	controllers = CommentController.class,
	excludeAutoConfiguration = {
		org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration.class
	}
)
class CommentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private CommentService commentService;

	@Test
	@DisplayName("POST /api/comments - 댓글 생성 성공")
	void save_댓글_생성_성공() throws Exception {
		// given
		String content = "테스트 댓글";
		Long scheduleId = 1L;
		Long userId = 1L;

		CommentRequest request = new CommentRequest(content, scheduleId, userId);

		given(commentService.save(any(CommentRequest.class)))
			.willReturn(null);

		// when & then
		mockMvc.perform(post("/api/comments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isCreated());

		verify(commentService).save(any(CommentRequest.class));
	}

	@Test
	@DisplayName("GET /api/comments - 일정별 댓글 조회 성공")
	void findByScheduleId_조회_성공() throws Exception {
		// given
		Long scheduleId = 1L;

		given(commentService.findByScheduleId(scheduleId))
			.willReturn(List.of());

		// when & then
		mockMvc.perform(get("/api/comments")
				.param("scheduleId", scheduleId.toString()))
			.andExpect(status().isOk());

		verify(commentService).findByScheduleId(scheduleId);
	}

	@Test
	@DisplayName("PUT /api/comments/{id} - 댓글 수정 성공")
	void update_댓글_수정_성공() throws Exception {
		// given
		Long commentId = 1L;
		String newContent = "수정된 댓글";
		Long scheduleId = 1L;
		Long userId = 1L;

		CommentRequest request = new CommentRequest(newContent, scheduleId, userId);

		given(commentService.update(eq(commentId), any(CommentRequest.class)))
			.willReturn(null);

		// when & then
		mockMvc.perform(put("/api/comments/{id}", commentId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk());

		verify(commentService).update(eq(commentId), any(CommentRequest.class));
	}

	@Test
	@DisplayName("DELETE /api/comments/{id} - 댓글 삭제 성공")
	void delete_댓글_삭제_성공() throws Exception {
		// given
		Long commentId = 1L;

		// when & then
		mockMvc.perform(delete("/api/comments/{id}", commentId))
			.andExpect(status().isNoContent());

		verify(commentService).delete(commentId);
	}
}