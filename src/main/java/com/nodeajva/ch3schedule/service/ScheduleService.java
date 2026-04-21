package com.nodeajva.ch3schedule.service;

import com.nodeajva.ch3schedule.entity.Schedule;
import com.nodeajva.ch3schedule.entity.User;
import com.nodeajva.ch3schedule.dto.request.ScheduleRequest;

import com.nodeajva.ch3schedule.dto.request.ScheduleUpdateRequest;
import com.nodeajva.ch3schedule.dto.response.ScheduleResponse;
import com.nodeajva.ch3schedule.exception.ScheduleNotFoundException;
import com.nodeajva.ch3schedule.exception.UserNotFoundException;
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



    //등록
    @Transactional
    public ScheduleResponse save(ScheduleRequest request){


        //확인
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException());

        Schedule schedule = new Schedule(
                request.title(),
                request.content(),
                user

        );
        //저장
        Schedule saved = scheduleRepository.save(schedule);

        //변환
        ScheduleResponse response = ScheduleResponse.from(saved);

        return response;
    }

    //단건 조회

    public ScheduleResponse findById(Long id){
        Schedule schedules = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException());

        return ScheduleResponse.from(schedules);
    }

    //페이징
    public Page<ScheduleResponse> findAllPaged(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        return schedules.map(ScheduleResponse::from);
    }


    // 전체 조회 (ID 순)
    public List<ScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponse> responses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            responses.add(ScheduleResponse.from(schedule));
        }

        return responses;
    }



    // 최신순 조회
    public List<ScheduleResponse> findRecent() {
        List<Schedule> schedules = scheduleRepository.findAllByOrderByCreatedAtDesc();

        List<ScheduleResponse> responses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            responses.add(ScheduleResponse.from(schedule));
        }

        return responses;
    }




    // 유저 글 전체 조회
    public List<ScheduleResponse> findByUserId(Long userid){
        List<Schedule> schedules = scheduleRepository.findByUserId(userid);

        List<ScheduleResponse> responses = new ArrayList<>();

        for (Schedule schedule: schedules){
            ScheduleResponse response = ScheduleResponse.from(schedule);
            responses.add(response);
        }
        return responses;
    }

    //수정
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

    //삭제
    @Transactional
    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException());

        scheduleRepository.delete(schedule);
    }
}
