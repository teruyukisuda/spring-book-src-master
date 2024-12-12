package com.example.myapp.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.example.myapp.repository.TrainingRepository;
import com.example.myapp.view.TrainingDto;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TrainingServiceImplTest {
    
    @InjectMocks
    TrainingServiceImpl trainingService;
    
    @Mock
    TrainingRepository trainingRepository;
    
    @Test
    public void testFindAll() {
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setTitle("トレ１");
        //when(trainingRepository.selectAll()).thenReturn(Arrays.asList(trainingDto));
        doReturn(Arrays.asList(trainingDto)).when(trainingRepository).selectAll();
        
        List<TrainingDto> all = trainingService.findAll();
        assertThat(all.size(), is(1));
        assertThat(all.get(0).getTitle(), is("トレ１"));
    }
    
    @Test
    public void testRegist() {
        
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setTitle("テストタイトル");
        trainingDto.setStart_date_time(LocalDateTime.of(2024,10,10,10,0));
        trainingDto.setEnd_date_time(LocalDateTime.of(2024,10,10,11,0));
        trainingDto.setCapacity(10);
        trainingDto.setReserved(1);
        
        trainingService.register(trainingDto);
        
        verify(trainingRepository).insert(any());
        
    }
    
}

