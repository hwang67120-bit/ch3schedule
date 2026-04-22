package com.nodeajva.ch3schedule.service;

import com.nodeajva.ch3schedule.entity.Schedule;
import com.nodeajva.ch3schedule.entity.User;
import com.nodeajva.ch3schedule.dto.request.ScheduleRequest;

import com.nodeajva.ch3schedule.dto.request.ScheduleUpdateRequest;
import com.nodeajva.ch3schedule.dto.response.ScheduleResponse;
import com.nodeajva.ch3schedule.exception.ScheduleNotFoundException;
import com.nodeajva.ch3schedule.exception.UserNotFoundException;
import com.nodeajva.ch3schedule.repository.CommentRepository;
import com.nodeajva.ch3schedule.repository.ScheduleRepository;
import com.nodeajva.ch3schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;


    /**
     * 일정 등록
     * 새로운 일정을 생성합니다.
     *
     * @param request 일정 등록 요청 (title, content, userId)
     * @return 생성된 일정 정보 (id, title, content, userId, userName, createdAt, updatedAt)
     * @throws UserNotFoundException 존재하지 않는 사용자인 경우
     */
    @Transactional
    public ScheduleResponse save(ScheduleRequest request){

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException());

        Schedule schedule = new Schedule(
                request.title(),
                request.content(),
                user

        );
        Schedule saved = scheduleRepository.save(schedule);


        ScheduleResponse response = ScheduleResponse.from(saved);

        return response;
    }

    /**
     * 일정 단건 조회
     * ID로 특정 일정을 조회합니다.
     *
     * @param id 일정 ID
     * @return 일정 정보 (id, title, content, userId, userName, createdAt, updatedAt)
     * @throws ScheduleNotFoundException 존재하지 않는 일정인 경우
     */
    public ScheduleResponse findById(Long id){
        Schedule schedules = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException());

        return ScheduleResponse.from(schedules);
    }

    /**
     * 일정 페이징 조회
     * 페이징 처리된 일정 목록을 조회합니다.
     *
     * @param pageable 페이징 정보 (page, size, sort)
     * @return 페이징된 일정 목록
     */
    public Page<ScheduleResponse> findAllPaged(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        return schedules.map(ScheduleResponse::from);
    }


    /**
     * 일정 전체 조회 (ID 순)
     * 모든 일정을 ID 순서대로 조회합니다.
     *
     * @return 일정 목록 (id, title, content, userId, userName, createdAt, updatedAt)
     */
    public List<ScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponse> responses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            responses.add(ScheduleResponse.from(schedule));
        }

        return responses;
    }



    /**
     * 일정 최신순 조회
     * 모든 일정을 생성일 기준 최신순으로 조회합니다.
     *
     * @return 최신순으로 정렬된 일정 목록
     */
    public List<ScheduleResponse> findRecent() {
        List<Schedule> schedules = scheduleRepository.findAllByOrderByCreatedAtDesc();

        List<ScheduleResponse> responses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            responses.add(ScheduleResponse.from(schedule));
        }

        return responses;
    }




    /**
     * 특정 사용자의 일정 전체 조회
     * 특정 사용자가 작성한 모든 일정을 조회합니다.
     *
     * @param userid 사용자 ID
     * @return 사용자의 일정 목록
     */
    public List<ScheduleResponse> findByUserId(Long userid){
        List<Schedule> schedules = scheduleRepository.findByUserId(userid);

        List<ScheduleResponse> responses = new ArrayList<>();

        for (Schedule schedule: schedules){
            ScheduleResponse response = ScheduleResponse.from(schedule);
            responses.add(response);
        }
        return responses;
    }

    /**
     * 일정 수정
     * 기존 일정의 제목과 내용을 수정합니다.
     *
     * @param id 일정 ID
     * @param request 일정 수정 요청 (title, content)
     * @return 수정된 일정 정보 (id, title, content, userId, userName, createdAt, updatedAt)
     * @throws ScheduleNotFoundException 존재하지 않는 일정인 경우
     */
    @Transactional
    public ScheduleResponse update(Long id, ScheduleUpdateRequest request){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()->new ScheduleNotFoundException());

        schedule.update(
                request.title(),
                request.content()

        );

        Schedule saved = scheduleRepository.save(schedule);


        return ScheduleResponse.from(saved);
    }

    /**
     * 일정 삭제
     * 일정과 관련된 모든 댓글을 함께 삭제합니다.
     *
     * @param id 일정 ID
     * @throws ScheduleNotFoundException 존재하지 않는 일정인 경우
     */
    @Transactional
    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException());

        commentRepository.deleteByScheduleId(id);

        scheduleRepository.delete(schedule);
    }
}
