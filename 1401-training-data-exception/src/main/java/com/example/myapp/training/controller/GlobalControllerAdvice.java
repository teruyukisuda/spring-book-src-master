package com.example.myapp.training.controller;

import com.example.myapp.training.input.TrainingAdminInput;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Exception.class)
    public String systemError(Exception ex) {
        ex.printStackTrace();
        return "systemError";
    }
//    @ExceptionHandler(DuplicateKeyException.class)
//    public String duplicate(Exception ex, Model model) {
//        return "admin/training/registrationForm";
//    }
//    @ExceptionHandler(DuplicateKeyException.class)
//    public ModelAndView duplicate(Exception ex) {
//        System.out.println("DuplicateKeyException handler called");
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("trainingAdminInput", new TrainingAdminInput());
//        mav.setViewName("admin/training/registrationForm");
//        mav.addObject("duplicateError", "キーが重複しました。別のキーを入力してください");
//        return mav;
//    }
}
