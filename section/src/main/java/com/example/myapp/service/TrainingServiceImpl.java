package com.example.myapp.service;

import com.example.myapp.repository.TrainingRepository;
import com.example.myapp.view.TrainingDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService {
    
    @Autowired
    private TrainingRepository trainingRepository;
    
    public List<TrainingDto> findAll(){
        return trainingRepository.selectAll();
    }

    @Override
    public TrainingDto findById(String id) {
        return trainingRepository.selectById(id);
    }
    
    public TrainingDto register(TrainingDto trainingDto){
        trainingRepository.insert(trainingDto);
        return trainingDto;
    }
    
}
