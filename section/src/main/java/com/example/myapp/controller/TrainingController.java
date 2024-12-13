package com.example.myapp.controller;

import com.example.myapp.service.TrainingService;
import com.example.myapp.view.TrainingDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping
    public String trainings(Model model) {
        List<TrainingDto> trainings = trainingService.findAll();
        model.addAttribute("trainings", trainings);
        return "trainings";
    }
    
    @GetMapping("/{id}")
    public String trainingDetail(Model model, @PathVariable String id) {
        TrainingDto trainingDto = trainingService.findById(id);
        model.addAttribute("trainingDto", trainingDto);
        return "trainingDetail";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("trainingDto", new TrainingDto());
        return "trainingForm";
    }

    @PostMapping("/create")
    public String createTraining(@Valid @ModelAttribute TrainingDto trainingDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "trainingForm";
        }

        trainingService.register(trainingDto);
        return "redirect:/trainings";

    }

}
