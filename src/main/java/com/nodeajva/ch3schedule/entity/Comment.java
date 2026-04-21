package com.nodeajva.ch3schedule.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // 생성자
    public Comment(String content, Schedule schedule, User user) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;

    }

    // update 메서드
    public void update(String content) {
        this.content = content;

    }
}
