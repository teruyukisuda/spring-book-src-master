package com.example.myapp.training;

import java.util.List;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.repository.JdbcTrainingRepository;
import com.example.myapp.training.repository.TrainingRepository;
import com.example.myapp.training.service.TrainingService;
import com.example.myapp.training.service.TrainingServiceImpl;

public class TrainingApplication {
    public static void main(String[] args) {
        TrainingRepository trainingRepository = new JdbcTrainingRepository();
        TrainingService trainingService = new TrainingServiceImpl(trainingRepository);

        List<Training> trainings = trainingService.findAll();
        for (Training training : trainings) {
            System.out.println(training.getTitle());
        }
    }
}

