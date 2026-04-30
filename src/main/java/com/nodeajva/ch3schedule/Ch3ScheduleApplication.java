package com.nodeajva.ch3schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ch3ScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch3ScheduleApplication.class, args);
    }

}
