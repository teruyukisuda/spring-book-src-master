package com.example.myapp.training.service;

import java.util.List;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.repository.TrainingRepository;

public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<Training> findAll() {
        return trainingRepository.selectAll();
    }

}
