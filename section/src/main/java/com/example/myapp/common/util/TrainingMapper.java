package com.example.myapp.common.util;

import com.example.myapp.repository.entity.TrainingEntity;
import com.example.myapp.view.TrainingDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

    TrainingDto entityToDto(TrainingEntity trainingEntity);

    TrainingEntity dtoToEntity(TrainingDto trainingDto);
    
    List<TrainingDto> entityToDto(List<TrainingEntity> trainings);
}

