package com.example.myapp.training.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.repository.TrainingRepository;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<Training> findAll() {
        return trainingRepository.selectAll();
    }

    @Override
    public Training findById(String id) {
        return trainingRepository.selectById(id);
    }

}
