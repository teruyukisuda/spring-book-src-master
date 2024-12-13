package com.example.myapp.controller;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.myapp.service.TrainingService;
import com.example.myapp.view.TrainingDto;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TrainingController.class)
public class TrainingControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    TrainingService trainingService;
   
    @Test
    @WithMockUser(roles = "ADMIN")
    public void test_tainings() throws Exception {
        ArrayList<TrainingDto> trainingDtos = new ArrayList<>();
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setTitle("研修１");
        trainingDtos.add(trainingDto);
        TrainingDto trainingDto2 = new TrainingDto();
        trainingDto2.setTitle("研修３");
        trainingDtos.add(trainingDto2);
        
        doReturn(trainingDtos).when(trainingService).findAll();
        
        mockMvc.perform(get("/trainings"))
            .andExpect(status().isOk())
            .andExpect(view().name("trainings"))
            .andExpect(content().string(containsString("研修１")))
            .andExpect(content().string(containsString("研修３")))
            .andDo(print());
            
                
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    public void test_trainingDetail() throws Exception {
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setTitle("研修３");
        doReturn(trainingDto).when(trainingService).findById("t01");
        
        mockMvc.perform(get("/trainings/t01"))
            .andExpect(status().isOk())
            .andExpect(view().name("trainingDetail"))
            .andExpect(content().string(containsString("研修３")))
            .andDo(print());
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    public void text_training_validate_error() throws Exception {
        mockMvc.perform(post("/trainings/create")
            .with(csrf())  // CSRFトークンを追加
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("title", ""))  // 空の文字列を送ってバリデーションエラーを発生させる
            .andExpect(status().is2xxSuccessful()) // validationエラーでもstatusは2xx 
            .andExpect(view().name("trainingForm"))
            .andExpect(model().attributeHasFieldErrorCode("trainingDto", "title", "NotBlank"))
            .andDo(print());
    }
    
    
}
