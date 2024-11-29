package com.example.myapp.controller;

import com.example.myapp.controller.form.UserForm;
import com.example.myapp.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


//アプリケーションコンテキスト/myapp
//application.propertiesで指定
@Controller
@RequestMapping("/sample")
public class SampleController {

    public SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }
    @GetMapping("/input")
    public String input(@ModelAttribute UserForm userForm, BindingResult bindingResult, Model model) {
        model.addAttribute("userForm", userForm);
        return "input";
    }

    @PostMapping(value = "/confirm", params = "doConfirm")
    public String confirm(@Validated UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        model.addAttribute("userForm", userForm);
        return "confirm";
    }
    @PostMapping(value = "/confirm", params = "getName")
    public String confirm(Model model) {
        String name = sampleService.name();
        model.addAttribute("fullName", name);
        return "sample";
    }

    @GetMapping("/name")
    public String displaySample(Model model) {
        String name = sampleService.name();
        model.addAttribute("fullName", "須田" + name);
        return "sample";
    }

    @GetMapping("/old")
    public String displayOld(Model model) {
        String old = sampleService.old();
        model.addAttribute("fullName", old);
        return "sample";
    }

    @GetMapping("/page")
    public String displayList(@RequestParam int max, @RequestParam("hoge") int page, Model model) {
        model.addAttribute("hoge", page + max);
        return "hoge";
    }

    @GetMapping("/page2")
    public String displayList2(Input input, Model model) {
        System.out.println("page2");
        model.addAttribute("hoge", input.max + input.hoge);
        return "hoge";
    }
    
    public static class Input {
        public int max;
        public int hoge;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getHoge() {
            return hoge;
        }

        public void setHoge(int hoge) {
            this.hoge = hoge;
        }
    }

}
