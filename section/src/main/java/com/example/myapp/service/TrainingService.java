package com.example.myapp.service;

import com.example.myapp.view.TrainingDto;
import java.util.List;

public interface TrainingService {
    List<TrainingDto> findAll();
    TrainingDto findById(String id);
    void register(TrainingDto dto);
}
