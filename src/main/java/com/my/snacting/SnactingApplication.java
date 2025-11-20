package com.my.snacting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SnactingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnactingApplication.class, args);
    }

}
