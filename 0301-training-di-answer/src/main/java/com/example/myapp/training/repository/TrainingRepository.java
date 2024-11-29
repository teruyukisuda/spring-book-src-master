package com.example.myapp.training.repository;

import java.util.List;

import com.example.myapp.training.entity.Training;

public interface TrainingRepository {
    List<Training> selectAll();
}
