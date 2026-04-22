package com.nodeajva.ch3schedule.controller;

import com.nodeajva.ch3schedule.dto.request.CommentRequest;
import com.nodeajva.ch3schedule.dto.response.CommentResponse;
import com.nodeajva.ch3schedule.dto.response.DeleteRespoinse;
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

    //등록
    @PostMapping("/api/schedules/{scheduleId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse save(@Valid @RequestBody CommentRequest request){

        return commentService.save(request);
    }

    //댓글조회
    @GetMapping("/api/schedules/{scheduleId}/comments")
    public List<CommentResponse> findByScheduleId(@PathVariable Long scheduleId){

        return commentService.findByScheduleId(scheduleId);

    }

    //수정
    @PutMapping("/api/comments/{id}")
    public CommentResponse update(
            @Valid
            @PathVariable Long id,
            @RequestBody CommentRequest request
    ){
        return commentService.update(id,request);
    }

    //삭제
    @DeleteMapping("/api/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public DeleteRespoinse delete(
            @PathVariable Long id
    ){
        commentService.delete(id);

        return new DeleteRespoinse("댓글 삭제 완료");
    }
}
