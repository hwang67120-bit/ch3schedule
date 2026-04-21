package com.nodeajva.ch3schedule.service;

import com.nodeajva.ch3schedule.entity.Comment;
import com.nodeajva.ch3schedule.entity.Schedule;
import com.nodeajva.ch3schedule.entity.User;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

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

        Comment saved = commentRepository.save(comment);

        CommentResponse response = CommentResponse.from(saved);

        return response;
    }

    //댓글 조회
    public List<CommentResponse> findByScheduleId(Long scheduleId){

        List<Comment> comments = commentRepository.findByScheduleId(scheduleId);

        List<CommentResponse> responses = new ArrayList<>();
        for (Comment comment: comments){
            responses.add(CommentResponse.from(comment));
        }

        return responses;

    }

    //수정
    public CommentResponse update(Long id, CommentRequest request){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException());

        comment.update(request.content());

        Comment saved = commentRepository.save(comment);
        return CommentResponse.from(saved);

    }

    //삭제
    @Transactional
    public void delete(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException());

        commentRepository.delete(comment);
    }
}
