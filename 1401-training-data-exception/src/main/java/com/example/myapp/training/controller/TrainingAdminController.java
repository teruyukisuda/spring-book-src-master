package com.example.myapp.training.controller;

import java.util.List;

import com.example.myapp.training.input.TrainingAdminInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.service.TrainingAdminService;

@Controller
@RequestMapping("/admin/training")
public class TrainingAdminController {
	private static final Logger logger = LoggerFactory.getLogger(TrainingAdminController.class);

	private final TrainingAdminService trainingAdminService;

	public TrainingAdminController(TrainingAdminService trainingAdminService) {
		this.trainingAdminService = trainingAdminService;
	}

	@GetMapping("/display-list")
	public String displayList(Model model) {
        if (logger.isDebugEnabled()) {
			logger.debug("display-list-debug");
			logger.info("display-list");
        }
        if (logger.isInfoEnabled()) {
            logger.info("display-list");
        }
		List<Training> trainings = trainingAdminService.findAll();
		model.addAttribute("trainingList", trainings);
		return "admin/training/trainingList";
	}

	@GetMapping("/display-registration-form")
	public String displayRegistrationForm(Model model) {
		TrainingAdminInput trainingAdminInput = new TrainingAdminInput();
		model.addAttribute("trainingAdminInput", trainingAdminInput);
		return "admin/training/registrationForm";
	}

	@PostMapping(value = "/validate-registration-input")
	public String validateRegistrationInput(@Validated TrainingAdminInput trainingAdminInput, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/training/registrationForm";
		}
		return "admin/training/registrationConfirmation";
	}

	@PostMapping(value = "/register", params = "correct")
	public String correctRegistrationInput(@Validated TrainingAdminInput trainingAdminInput) {
		return "admin/training/registrationForm";
	}

	@PostMapping(value = "/register", params = "register")
	public String register(@Validated TrainingAdminInput trainingAdminInput, Model model, BindingResult bindingResult) {
		
		
        try {
            trainingAdminService.register(trainingAdminInput);
        } catch (DuplicateKeyException e) {
			model.addAttribute("duplicateError", "キーが重複してる");
			return "admin/training/registrationForm";
        }
		return "redirect:/admin/training/registrationComplete";
	}


}
