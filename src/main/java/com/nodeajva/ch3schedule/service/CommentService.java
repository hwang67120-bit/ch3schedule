package com.nodeajva.ch3schedule.service;

import com.nodeajva.ch3schedule.Entity.Comment;
import com.nodeajva.ch3schedule.Entity.Schedule;
import com.nodeajva.ch3schedule.Entity.User;
import com.nodeajva.ch3schedule.dto.request.CommentRequest;
import com.nodeajva.ch3schedule.dto.response.CommentResponse;
import com.nodeajva.ch3schedule.exception.CommentNotFoundException;
import com.nodeajva.ch3schedule.exception.ScheduleNotFoundException;
import com.nodeajva.ch3schedule.exception.UserNotFoundException;
import com.nodeajva.ch3schedule.repository.CommentRepository;
import com.nodeajva.ch3schedule.repository.ScheduleRepository;
import com.nodeajva.ch3schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository CommentRepository;

    //등록
    public CommentResponse save(CommentRequest request) {

        Schedule schedule = scheduleRepository.findById(request.scheduleId())
                .orElseThrow(() -> new ScheduleNotFoundException());

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException());

        Comment comment = new Comment(
                request.content(),
                schedule,
                user
        );

        Comment saved = CommentRepository.save(comment);

        CommentResponse response = CommentResponse.from(saved);

        return response;
    }

    //댓글 조회
    public List<CommentResponse> findByScheduleId(Long scheduleId){

        List<Comment> comments = CommentRepository.findByScheduleId(scheduleId);

        List<CommentResponse> responses = new ArrayList<>();
        for (Comment comment: comments){
            responses.add(CommentResponse.from(comment));
        }

        return responses;

    }

    //수정
    public CommentResponse update(Long id, CommentRequest request){
        Comment comment = CommentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException());

        comment.update(request.content());

        Comment saved = CommentRepository.save(comment);
        return CommentResponse.from(saved);

    }

    //삭제
    public void delete(Long id){
        Comment comment = CommentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException());

        CommentRepository.delete(comment);
    }
}
