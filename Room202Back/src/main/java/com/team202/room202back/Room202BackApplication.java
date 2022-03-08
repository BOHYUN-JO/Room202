package com.team202.room202back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class Room202BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(Room202BackApplication.class, args);
    }

}
