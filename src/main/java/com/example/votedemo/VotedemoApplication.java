package com.example.votedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.votedemo.mapper")
@EnableScheduling
public class VotedemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VotedemoApplication.class, args);
    }

}
