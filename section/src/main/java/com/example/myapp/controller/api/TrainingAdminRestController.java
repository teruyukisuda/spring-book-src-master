package com.example.myapp.controller.api;

import com.example.myapp.service.TrainingService;
import com.example.myapp.view.TrainingDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrainingAdminRestController {

    @Autowired
    public TrainingService trainingService;

    protected final Log logger = LogFactory.getLog(getClass());

    public static class Training {

        public Training(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String id;
        public String name;
    }

    public static class TrainingAdminInput {

        private String id;
        @NotBlank
        private String title;
        @NotNull
        private LocalDateTime startDateTime;
        @NotNull
        private LocalDateTime endDateTime;
        @NotNull
        @Min(0)
        private Integer reserved;
        @NotNull
        @Min(1)
        private Integer capacity;

        public Integer getCapacity() {
            return capacity;
        }

        public void setCapacity(Integer capacity) {
            this.capacity = capacity;
        }

        public Integer getReserved() {
            return reserved;
        }

        public void setReserved(Integer reserved) {
            this.reserved = reserved;
        }

        public LocalDateTime getEndDateTime() {
            return endDateTime;
        }

        public void setEndDateTime(LocalDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }

        public LocalDateTime getStartDateTime() {
            return startDateTime;
        }

        public void setStartDateTime(LocalDateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    @GetMapping("/trainings")
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        List<TrainingDto> all = trainingService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/trainings/{id}")
    public ResponseEntity<TrainingDto> hello(@PathVariable String id) {
        TrainingDto training = trainingService.findById(id);
        return ResponseEntity.ok().eTag("xym1").header("abc", "efg").body(training);
    }

    @PutMapping("/trainings/{id}")
    public void update(@PathVariable String id,
        @Validated @RequestBody TrainingAdminInput training) {
        logger.debug(training.getTitle());
        System.out.println(training.getTitle());
    }

    @PostMapping("/trainings")
    public ResponseEntity<TrainingDto> create(@RequestBody TrainingDto training) {
        TrainingDto created = trainingService.register(training);
        return ResponseEntity.created(URI.create("/trainings/" + created.getId())).body(created);
    }


}
