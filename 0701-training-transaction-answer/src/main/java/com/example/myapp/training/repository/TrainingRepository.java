package com.example.myapp.training.repository;

import com.example.myapp.training.entity.Training;

public interface TrainingRepository {
    boolean update(Training training);

    Training selectById(String id);
}
