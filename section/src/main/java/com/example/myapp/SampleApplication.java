package com.example.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SampleApplication {
    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(SampleApplication.class, args);
    }
}
