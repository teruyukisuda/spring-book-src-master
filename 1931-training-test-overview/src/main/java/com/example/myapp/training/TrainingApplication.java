package com.example.myapp.training;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.service.TrainingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TrainingApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TrainingApplication.class);
        TrainingService trainingService = context.getBean(TrainingService.class);
        Training training = trainingService.findById("t01");
        System.out.println(training.getTitle());
    }
}

