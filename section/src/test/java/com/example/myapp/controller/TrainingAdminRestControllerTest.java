package com.example.myapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.myapp.controller.api.TrainingAdminRestController;
import com.example.myapp.service.TrainingService;
import com.example.myapp.view.TrainingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TrainingAdminRestController.class)
public class TrainingAdminRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrainingService trainingService;

    @Test
    void test_registerTraining() throws Exception {
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId("t99");
        doReturn(trainingDto).when(trainingService).register(any());

        String requestBody = """
            {
            "title": "SQL入門",
            "startDateTime": "2021-12-01T09:30:00",
            "endDateTime": "2021-12-01T11:30:00",
            "reserved": 0,
            "capacity": 8,
            }
            """;

        mockMvc.perform(
                post("/api/trainings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
            )
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/api/training/t99"));
    }
}
