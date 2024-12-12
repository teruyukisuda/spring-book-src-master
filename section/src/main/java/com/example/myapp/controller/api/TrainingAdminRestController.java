package com.example.myapp.controller.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingAdminRestController {

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

    @GetMapping("/api/training/{id}")
    public ResponseEntity<Training> hello(@PathVariable String id) {
        return ResponseEntity.ok().eTag("xym1").header("abc", "efg").body(new Training(id, "end fo "
            + "world"));
    }
    
    @PutMapping("/api/training/{id}")
    public void update(@PathVariable String id, @Validated @RequestBody TrainingAdminInput training) {
        logger.debug(training.getTitle());
        System.out.println(training.getTitle());
    }
    
    

}
