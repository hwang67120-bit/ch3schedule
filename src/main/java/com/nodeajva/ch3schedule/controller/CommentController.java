package com.nodeajva.ch3schedule.controller;

import com.nodeajva.ch3schedule.dto.request.CommentRequest;
import com.nodeajva.ch3schedule.dto.response.CommentResponse;
import com.nodeajva.ch3schedule.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 등록 API
     *
     * @param request 댓글 등록 요청 (scheduleId, userId, content)
     * @return 생성된 댓글 정보
     */    @PostMapping("/api/schedules/{scheduleId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse save(@Valid @RequestBody CommentRequest request){

        return commentService.save(request);
    }

    /**
     * 일정의 댓글 조회 API
     *
     * @param scheduleId 일정 ID
     * @return 댓글 목록
     */
    @GetMapping("/api/schedules/{scheduleId}/comments")
    public List<CommentResponse> findByScheduleId(@PathVariable Long scheduleId){

        return commentService.findByScheduleId(scheduleId);

    }

    /**
     * 댓글 수정 API
     *
     * @param id 댓글 ID
     * @param request 댓글 수정 요청 (scheduleId, userId, content)
     * @return 수정된 댓글 정보
     */
    @PutMapping("/api/comments/{id}")
    public CommentResponse update(
            @Valid
            @PathVariable Long id,
            @RequestBody CommentRequest request
    ){
        return commentService.update(id,request);
    }

    /**
     * 댓글 삭제 API
     *
     * @param id 댓글 ID
     */
    @DeleteMapping("/api/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ){
        commentService.delete(id);


    }
}
