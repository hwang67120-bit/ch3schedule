package com.nodeajva.ch3schedule.controller;

import com.nodeajva.ch3schedule.dto.request.ScheduleRequest;
import com.nodeajva.ch3schedule.dto.request.ScheduleUpdateRequest;
import com.nodeajva.ch3schedule.dto.response.ScheduleResponse;
import com.nodeajva.ch3schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //등록
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleResponse save(@Valid @RequestBody ScheduleRequest request){

        return scheduleService.save(request);
    }

    //단건조회

    @GetMapping("/{id}")
    public ScheduleResponse findById (@PathVariable Long id){
        return scheduleService.findById(id);
    }

    // 전체조회
    @GetMapping
    public List<ScheduleResponse> findAll(){

        return scheduleService.findAll();
    }

    //페이징 조회
    @GetMapping("/Paged")
    public Page<ScheduleResponse> findAllPaged(Pageable pageable) {
        return scheduleService.findAllPaged(pageable);
    }

    //유저 일정들 조회
    @GetMapping("/user/{userId}")
    public List<ScheduleResponse> findByUserId(@PathVariable Long userId) {

        return scheduleService.findByUserId(userId);
    }

    //최신 일정조회
    @GetMapping("/recent")
    public List<ScheduleResponse> findRecent() {
        return scheduleService.findRecent();
    }

    //수정
    @PutMapping("/{id}")
    public ScheduleResponse update(
            @Valid
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequest request){
        return scheduleService.update(id,request);
    }

    //삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ){
        scheduleService.delete(id);
    }
}
