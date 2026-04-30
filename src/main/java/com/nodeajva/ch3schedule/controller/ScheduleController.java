package com.nodeajva.ch3schedule.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nodeajva.ch3schedule.dto.request.ScheduleRequest;
import com.nodeajva.ch3schedule.dto.request.ScheduleUpdateRequest;
import com.nodeajva.ch3schedule.dto.response.ScheduleResponse;
import com.nodeajva.ch3schedule.service.ScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;

	/**
	 * 일정 등록 API
	 *
	 * @param request 일정 등록 요청 (title, content, userId)
	 * @return 생성된 일정 정보
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ScheduleResponse save(@Valid @RequestBody ScheduleRequest request) {

		return scheduleService.save(request);
	}

	/**
	 * 일정 단건 조회 API
	 *
	 * @param id 일정 ID
	 * @return 일정 정보
	 */
	@GetMapping("/{id}")
	public ScheduleResponse findById(@PathVariable Long id) {
		return scheduleService.findById(id);
	}

	/**
	 * 일정 전체 조회 API
	 *
	 * @return 일정 목록
	 */
	@GetMapping
	public List<ScheduleResponse> findAll() {

		return scheduleService.findAll();
	}

	/**
	 * 일정 페이징 조회 API
	 *
	 * @param pageable 페이징 정보 (page, size, sort)
	 * @return 페이징된 일정 목록
	 */
	@GetMapping("/Paged")
	public Page<ScheduleResponse> findAllPaged(Pageable pageable) {
		return scheduleService.findAllPaged(pageable);
	}

	/**
	 * 특정 사용자 일정 조회 API
	 *
	 * @param userId 사용자 ID
	 * @return 사용자의 일정 목록
	 */
	@GetMapping("/user/{userId}")
	public List<ScheduleResponse> findByUserId(@PathVariable Long userId) {

		return scheduleService.findByUserId(userId);
	}

	/**
	 * 최신 일정 조회 API
	 *
	 * @return 최신순으로 정렬된 일정 목록
	 */
	@GetMapping("/recent")
	public List<ScheduleResponse> findRecent() {
		return scheduleService.findRecent();
	}

	/**
	 * 일정 수정 API
	 *
	 * @param id      일정 ID
	 * @param request 일정 수정 요청 (title, content)
	 * @return 수정된 일정 정보
	 */
	@PutMapping("/{id}")
	public ScheduleResponse update(
		@PathVariable Long id,
		@Valid
		@RequestBody ScheduleUpdateRequest request) {
		return scheduleService.update(id, request);
	}

	/**
	 * 일정 삭제 API
	 *
	 * @param id 일정 ID
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		scheduleService.delete(id);

	}
}
