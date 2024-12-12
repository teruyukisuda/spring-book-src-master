package com.example.myapp.repository;

import com.example.myapp.view.TrainingDto;
import java.util.List;

public interface TrainingRepository {
    public List<TrainingDto> selectAll();
    public boolean insert(TrainingDto trainingDto);
}
