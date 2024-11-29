package com.example.myapp.training.service;

import java.util.List;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.input.TrainingAdminInput;

public interface TrainingAdminService {
	List<Training> findAll();

	Training findById(String trainingId);

	void update(TrainingAdminInput trainingAdminInput);

	void delete(String id);

	Training register(TrainingAdminInput trainingAdminInput);
}
