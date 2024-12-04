package com.example.myapp.training.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.repository.TrainingRepository;

class TrainingServiceImplTest {
    @Test
    public void test_findAll() {
        TrainingRepository trainingRepository = new MockTrainingRepository();
        TrainingService trainingService = new TrainingServiceImpl(trainingRepository);

        List<Training> trainings = trainingService.findAll();
        // 結果の確認
        Assertions.assertThat(trainings.size()).isEqualTo(10);
    }
}