package com.nodeajva.ch3schedule.repository;

import com.nodeajva.ch3schedule.entity.Schedule;
import com.nodeajva.ch3schedule.exception.ScheduleNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * 사용자 ID로 일정 조회
     *
     * @param userid 사용자 ID
     * @return 사용자의 일정 목록
     */
    List<Schedule> findByUserId(Long userid);

    /**
     * ID로 일정 조회
     *
     * @param id 일정 ID
     * @return 일정 엔티티
     * @throws ScheduleNotFoundException 일정을 찾을 수 없는 경우
     */
    default Schedule load(Long id) {
        return findById(id).orElseThrow(ScheduleNotFoundException::new);
    }

    /**
     * 최신순 일정 조회 (JPQL)
     *
     * @return 생성일 기준 내림차순 정렬된 일정 목록
     */
    @Query("SELECT s FROM Schedule s ORDER BY s.createdAt  DESC")
    List<Schedule> findRecent();
}
