package com.example.myapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingAdminRestController {

    public static class Training {

        public Training(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String id;
        public String name;
    }

    @GetMapping("/api/training/{id}")
    public ResponseEntity<Training> hello(@PathVariable String id) {
        return ResponseEntity.ok().eTag("xym1").header("abc", "efg").body(new Training(id, "end fo "
            + "world"));
    }

    ;
}
