package com.example.myfirstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyFirstProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFirstProjectApplication.class, args);
    }

}
