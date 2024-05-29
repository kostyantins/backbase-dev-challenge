package com.backbasedevchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BackbaseDevChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackbaseDevChallengeApplication.class, args);
    }
}
