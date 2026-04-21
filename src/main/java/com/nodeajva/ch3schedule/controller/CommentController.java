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
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //등록
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse save(@Valid @RequestBody CommentRequest request){

        return commentService.save(request);
    }

    //댓글조회
    @GetMapping("/{scheduleId}")
    public List<CommentResponse> findByScheduleId(@PathVariable Long scheduleId){

        return commentService.findByScheduleId(scheduleId);

    }

    //수정
    @PutMapping("/{id}")
    public CommentResponse update(
            @Valid
            @PathVariable Long id,
            @RequestBody CommentRequest request
    ){
        return commentService.update(id,request);
    }

    //삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ){
        commentService.delete(id);
    }
}
