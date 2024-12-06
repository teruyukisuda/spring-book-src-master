package com.example.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping(value = "/login", params = "failure")
    public String loginFailure(Model model) {
        model.addAttribute("failureMessage", "ログインに失敗しました");
        return "login";
    }
    
    @RequestMapping("/display-access-denied")
    public String displayAccessDenied() {
        return "access-denied";
    }
}
