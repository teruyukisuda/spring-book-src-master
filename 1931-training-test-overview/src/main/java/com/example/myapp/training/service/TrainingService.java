package com.example.myapp.training.service;

import com.example.myapp.training.entity.Training;

import java.util.List;

public interface TrainingService {
	List<Training> findAll();

	Training findById(String trainingId);
}
