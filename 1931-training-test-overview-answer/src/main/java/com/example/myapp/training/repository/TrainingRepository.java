package com.example.myapp.training.repository;

import com.example.myapp.training.entity.Training;

import java.util.List;

public interface TrainingRepository {
    Training selectById(String id);

    List<Training> selectAll();

    boolean update(Training training);
}
