package com.example.myapp.training.service;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.input.TrainingAdminInput;

public interface TrainingAdminService {
	Training register(TrainingAdminInput trainingAdminInput);
}
