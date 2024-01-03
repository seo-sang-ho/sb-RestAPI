package com.ll.sbrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SbRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbRestApiApplication.class, args);
    }

}
